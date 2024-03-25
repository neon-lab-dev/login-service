package com.neonlab.loginservice.service;

import com.neonlab.common.config.Constants;
import com.neonlab.common.dto.*;
import com.neonlab.common.enums.OtpStatus;
import com.neonlab.common.enums.SMSProvider;
import com.neonlab.common.enums.VerificationMode;
import com.neonlab.common.expectations.ServerException;
import com.neonlab.common.services.AuthUserService;
import com.neonlab.common.services.UserService;
import com.neonlab.common.utilities.DateUtils;
import com.neonlab.common.utilities.OtpUtil;
import com.neonlab.common.entities.Otp;
import com.neonlab.common.entities.SystemConfig;
import com.neonlab.common.repositories.OtpRepository;
import com.neonlab.common.repositories.SystemConfigRepository;
import com.neonlab.loginservice.service.sms.SmsService;
import com.neonlab.loginservice.service.sms.SmsServiceFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

import static com.neonlab.common.enums.OtpStatus.*;
import static com.neonlab.common.enums.OtpStatus.EXPIRED;

@Service
@AllArgsConstructor
public class OtpService {

    private final SystemConfigRepository systemConfigRepository;
    private final OtpRepository otpRepository;
    private final AuthUserService authUserService;

    private static final String OTP_SENT = "OTP send successfully to %s";

    public String send(PhoneNoVerificationRequest request) throws ServerException {
        Otp otpEntity = new Otp();
        otpEntity.setPurpose(request.getPurpose());

        if (Objects.nonNull(request.getPhoneNo())) {
            SystemConfig systemConfig = getSystemConfig(Constants.SMS_KEY);
            SmsService smsService = SmsServiceFactory.getSmsService(SMSProvider.fromString(systemConfig.getValue()));
            String otp = OtpUtil.generateOtp(4);
            SmsRequest smsRequest = new SmsRequest();
            smsRequest.setOtp(otp);
            smsRequest.setPhone(request.getPhoneNo());
            smsRequest.setSender("SENDER_NAME");
            smsRequest.setMessage(String.format("Your otp for sign up is %s", otp));
            smsRequest.setPurpose("SIGN_UP");

            otpEntity.setServiceProvider(smsService.getProvider().name());
            otpEntity.setOtp(otp);
            otpEntity.setCommunicatedTo(request.getPhoneNo());
            String expiryMinutes = systemConfigRepository.findByKey(Constants.OTP_EXPIRY_SMS).getValue();
            otpEntity.setExpiryTime(DateUtils.getDateAfterNMinutes(Integer.parseInt(expiryMinutes)));
            otpEntity.setVerificationMode(VerificationMode.valueOf(request.getMode()));
            otpEntity.setCreatedAt(new Date());
            otpEntity.setModifiedAt(new Date());

            try {
                smsService.sendSMS(smsRequest);
            } catch (Exception e) {
                otpEntity.setStatus(FAILED);
                otpRepository.save(otpEntity);
                throw new ServerException("Error while sending sms : " + e.getMessage());
            }
        }
        otpEntity.setStatus(SENT);
        otpRepository.save(otpEntity);
        return String.format(OTP_SENT, request.getPhoneNo());
    }

    // to move in commons after moving entity & repo
    private SystemConfig getSystemConfig(String smsKey) {
        SystemConfig systemConfig = systemConfigRepository.findByKey(smsKey);
        if (Objects.isNull(systemConfig)) {
            throw new IllegalArgumentException("No Config defined in system config for key  " + smsKey);
        }
        return systemConfig;
    }


    @Transactional
    public VerificationResponse verify(PhoneNoVerificationRequest request) throws ServerException {
        String communicatedTo = request.getPhoneNo();
        var otp = fetchOtpByCommunicatedToAndStatusAndPurpose(communicatedTo, SENT, request.getPurpose());
        if (isValid(otp, request)){
            otp.setStatus(VERIFIED);
            otp.setModifiedAt(new Date());
            otpRepository.save(otp);
            var auth = authUserService.createAuthUser(otp, request);
            return new VerificationResponse(auth.getId(), VERIFIED.name());
        }
       throw handleVerificationException(otp, request);
    }

    private ServerException handleVerificationException(Otp otp, PhoneNoVerificationRequest request) throws ServerException {
        if (otp.getExpiryTime().before(new Date())){
            otp.setModifiedAt(new Date());
            otp.setStatus(EXPIRED);
            otpRepository.save(otp);
            return new ServerException("Otp Expired. Please re-send new otp.");
        }
        if (!Objects.equals(request.getOtp(), otp.getOtp())){
            return new ServerException("Invalid Otp. Please enter correct Otp.");
        }
        throw new ServerException("Undefined Exception");
    }

    private boolean isValid(Otp otp, PhoneNoVerificationRequest request){
        return (otp.getExpiryTime().after(new Date()) && Objects.equals(request.getOtp(), otp.getOtp()));
    }

    public Otp fetchOtpByCommunicatedToAndStatusAndPurpose(String communicatedTo, OtpStatus status, String purpose) throws ServerException {
        var retVal = otpRepository.findFirstByCommunicatedToAndStatusAndPurposeOrderByCreatedAtDesc(communicatedTo, status, purpose);
        if (retVal.isPresent()){
            return retVal.get();
        }
        throw new ServerException(String.format("No Otp record found with communication to as %s , status as %s and purpose as %s"
                , communicatedTo, status, purpose));
    }
}
