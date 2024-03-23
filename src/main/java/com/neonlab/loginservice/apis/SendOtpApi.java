package com.neonlab.loginservice.apis;

import com.mysql.cj.util.StringUtils;
import com.neonlab.common.config.Constants;
import com.neonlab.common.dto.Authenticationdto;
import com.neonlab.common.dto.EmailProvider;
import com.neonlab.common.dto.SMSProvider;
import com.neonlab.common.dto.SmsRequest;
import com.neonlab.common.utils.DateUtils;
import com.neonlab.common.utils.OtpUtil;
import com.neonlab.loginservice.entity.Otp;
import com.neonlab.loginservice.entity.SystemConfig;
import com.neonlab.loginservice.repository.OtpRepository;
import com.neonlab.loginservice.repository.SystemConfigRepository;
import com.neonlab.loginservice.service.email.EmailService;
import com.neonlab.loginservice.service.email.EmailServiceFactory;
import com.neonlab.loginservice.service.sms.SmsService;
import com.neonlab.loginservice.service.sms.SmsServiceFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
@AllArgsConstructor
public class SendOtpApi {

    private final SystemConfigRepository systemConfigRepository;
    private final OtpRepository otpRepository;
    public String send(Authenticationdto authenticationdto){
        Otp otpEntity = new Otp();
        otpEntity.setPurpose(authenticationdto.getVerificationPurpose());

        if (Objects.nonNull(authenticationdto.getPhoneNo())) {
            SystemConfig systemConfig = getSystemConfig(Constants.SMS_KEY);
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
            SystemConfig systemConfig = getSystemConfig(Constants.EMAIL_KEY);
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
        return "SUCCESS";
    }

    // to move in commons after moving entity & repo
    private SystemConfig getSystemConfig(String smsKey) {
        SystemConfig systemConfig = systemConfigRepository.findByKey(smsKey);
        if (Objects.isNull(systemConfig) || StringUtils.isNullOrEmpty(systemConfig.getValue())) {
            throw new IllegalArgumentException("No Config defined in system config for key  " + smsKey);
        }
        return systemConfig;
    }
}
