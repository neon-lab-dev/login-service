package com.neonlab.loginservice.apis;

import com.mysql.cj.util.StringUtils;
import com.neonlab.common.dto.AuthUserDto;
import com.neonlab.common.dto.VerificationReqDto;
import com.neonlab.loginservice.service.VerifyOtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OtpVerifyApi {
    private final VerifyOtpService verifyOtpService;

    public AuthUserDto verify(VerificationReqDto verificationReqDto) {
        validateRequest(verificationReqDto);
        return verifyOtpService.verify(verificationReqDto);
    }

    private void validateRequest(VerificationReqDto verificationReqDto) {
        if (StringUtils.isNullOrEmpty(verificationReqDto.getOtp())) {
            throw new RuntimeException("Otp is Required");
        }
        if (StringUtils.isNullOrEmpty(verificationReqDto.getVerificationPurpose())) {
            throw new RuntimeException("Purpose is Required");
        }
        if (StringUtils.isNullOrEmpty(verificationReqDto.getPhoneNo()) && StringUtils.isNullOrEmpty(verificationReqDto.getEmail())) {
            throw new RuntimeException("Phone or Email is Required");
        }
    }
}
