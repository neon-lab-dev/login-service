package com.neonlab.loginservice.apis;

import com.mysql.cj.util.StringUtils;
import com.neonlab.loginservice.config.Constants;
import com.neonlab.loginservice.dto.AuthUserDto;
import com.neonlab.loginservice.dto.Authenticationdto;
import com.neonlab.loginservice.dto.VerificationReqDto;
import com.neonlab.loginservice.entity.Otp;
import com.neonlab.loginservice.entity.SystemConfig;
import com.neonlab.loginservice.model.enums.EmailProvider;
import com.neonlab.loginservice.model.enums.SMSProvider;
import com.neonlab.loginservice.model.enums.SmsRequest;
import com.neonlab.loginservice.repository.OtpRepository;
import com.neonlab.loginservice.repository.SystemConfigRepository;
import com.neonlab.loginservice.service.email.EmailService;
import com.neonlab.loginservice.service.email.EmailServiceFactory;
import com.neonlab.loginservice.service.sms.SmsService;
import com.neonlab.loginservice.service.sms.SmsServiceFactory;
import com.neonlab.loginservice.util.DateUtils;
import com.neonlab.loginservice.util.OtpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class OtpApi {

    private final SystemConfigRepository systemConfigRepository;
    private final OtpRepository otpRepository;
    private final SignUpApi signUpApi;

    @Transactional
    public String sendOtp(Authenticationdto authenticationdto) {

        Otp otpEntity = new Otp();
        otpEntity.setPurpose(authenticationdto.getVerificationPurpose());

        if (Objects.nonNull(authenticationdto.getPhoneNo())) {
            SystemConfig systemConfig = systemConfigRepository.findByKey(Constants.SMS_KEY);
            if (Objects.isNull(systemConfig) || StringUtils.isNullOrEmpty(systemConfig.getValue())) {
                throw new IllegalArgumentException("No Config defined in system config for key  " + Constants.SMS_KEY);
            }
            SmsService smsService = SmsServiceFactory.getSmsService(SMSProvider.fromString(systemConfig.getValue()));
            String otp = OtpUtil.generateOtp(4);
            SmsRequest request = new SmsRequest();
            request.setOtp(otp);
            request.setPhone(authenticationdto.getPhoneNo());
            request.setSender("SENDER_NAME");
            request.setMessage(String.format("Your otp for sign up is %s", otp));
            request.setPurpose("SIGN_UP");

            otpEntity.setServiceProvider(smsService.getProvider().name());
            otpEntity.setOtp(otp);
            otpEntity.setCommunicatedTo(authenticationdto.getPhoneNo());
            String expiryMinutes = systemConfigRepository.findByKey(Constants.OTP_EXPIRY_SMS).getValue();
            otpEntity.setExpiryTime(DateUtils.getDateAfterNMinutes(Integer.parseInt(expiryMinutes)));
            otpEntity.setVerificationMode("SMS");

            try {
                smsService.sendSMS(request);
            } catch (Exception e) {
                otpEntity.setStatus("FAILED");
                otpEntity.setCreatedAt(new Date());
                otpEntity.setModifiedAt(new Date());
                otpRepository.save(otpEntity);
                throw new RuntimeException("Error while sending sms : " + e.getMessage());
            }
        }

        if (Objects.nonNull(authenticationdto.getEmail())) {
            SystemConfig systemConfig = systemConfigRepository.findByKey(Constants.EMAIL_KEY);
            if (Objects.isNull(systemConfig) || StringUtils.isNullOrEmpty(systemConfig.getValue())) {
                throw new IllegalArgumentException("No Config defined in system config for key  " + Constants.EMAIL_KEY);
            }
            EmailService emailService = EmailServiceFactory.getEmailService(EmailProvider.fromString(systemConfig.getValue()));
            String otp = OtpUtil.generateOtp(6);
            otpEntity.setServiceProvider(emailService.getProvider().name());
            otpEntity.setOtp(otp);
            otpEntity.setCommunicatedTo(authenticationdto.getPhoneNo());
            String expiryMinutes = systemConfigRepository.findByKey(Constants.OTP_EXPIRY_SMS).getValue();
            otpEntity.setExpiryTime(DateUtils.getDateAfterNMinutes(Integer.parseInt(expiryMinutes)));
            otpEntity.setVerificationMode("EMAIL");
            //TODO: add request later
            emailService.sendMail();

        }
        otpEntity.setStatus("SENT");
        otpEntity.setCreatedAt(new Date());
        otpEntity.setModifiedAt(new Date());
        otpRepository.save(otpEntity);
        return "Success";
    }

    @Transactional
    public AuthUserDto verifyOtp(VerificationReqDto verificationReqDto) {

        // Only SignUp flow ->
        String communicatedTo = verificationReqDto.getPhoneNo() != null ? verificationReqDto.getPhoneNo() : verificationReqDto.getEmail();

        Otp otp = otpRepository.findFirstByCommunicatedToAndStatusOrderByCreatedAt(communicatedTo, "SENT");
        if (otp == null || !otp.getPurpose().equals(verificationReqDto.getVerificationPurpose())) {
            throw new RuntimeException("No Otp pending to be verified for the user yet");
        }
        if (otp.getExpiryTime().before(new Date())) {
            throw new RuntimeException("Otp is Expired, Please send new Otp");
        }
        if (!otp.getOtp().equals(verificationReqDto.getOtp())) {
            // set failure count here if Required in Otp and check for retry attempt as well
            throw new RuntimeException("Otp does not match with the existing otp");
        }
        // verify Otp
        otp.setStatus("VERIFIED");
        otp.setModifiedAt(new Date());
        otpRepository.save(otp);

        log.info("Otp verified for user , creating auth details");
        return signUpApi.process(verificationReqDto);
    }


}



