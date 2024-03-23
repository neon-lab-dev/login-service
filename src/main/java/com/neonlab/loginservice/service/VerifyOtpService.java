package com.neonlab.loginservice.apis;

import com.neonlab.common.dto.AuthUserDto;
import com.neonlab.common.dto.VerificationReqDto;
import com.neonlab.loginservice.entity.Otp;
import com.neonlab.loginservice.repository.OtpRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class VerifyOtpApi {

    private final OtpRepository otpRepository;
    private final SignUpApi signUpApi;

    @Transactional
    public AuthUserDto verify(VerificationReqDto verificationReqDto){
        // Only SignUp flow ->
        String communicatedTo = verificationReqDto.getPhoneNo() != null ? verificationReqDto.getPhoneNo() : verificationReqDto.getEmail();

        Otp otp = otpRepository.findFirstByCommunicatedToAndStatusOrderByCreatedAtDesc(communicatedTo, "SENT");
        if (otp == null || !otp.getPurpose().equals(verificationReqDto.getVerificationPurpose())) {
            throw new RuntimeException("No Otp pending to be verified for the user yet");
        }
        if (otp.getExpiryTime().before(new Date())) {
            // mark otp as expired in DB // check status CANCELLED
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
