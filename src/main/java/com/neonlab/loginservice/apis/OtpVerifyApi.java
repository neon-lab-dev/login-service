package com.neonlab.loginservice.apis;

import com.neonlab.common.annotations.Loggable;
import com.neonlab.common.dto.AuthUserDto;
import com.neonlab.common.dto.VerificationReqDto;
import com.neonlab.common.utilities.StringUtil;
import com.neonlab.loginservice.service.VerifyOtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Loggable
@RequiredArgsConstructor
public class OtpVerifyApi {
    private final VerifyOtpService verifyOtpService;

    public AuthUserDto verify(VerificationReqDto verificationReqDto) {
        validateRequest(verificationReqDto);
        return verifyOtpService.verify(verificationReqDto);
    }

    private void validateRequest(VerificationReqDto verificationReqDto) {
        if (StringUtil.isNullOrEmpty(verificationReqDto.getOtp())) {
            throw new RuntimeException("Otp is Required");
        }
        if (StringUtil.isNullOrEmpty(verificationReqDto.getVerificationPurpose())) {
            throw new RuntimeException("Purpose is Required");
        }
        if (StringUtil.isNullOrEmpty(verificationReqDto.getPhoneNo()) && StringUtil.isNullOrEmpty(verificationReqDto.getEmail())) {
            throw new RuntimeException("Phone or Email is Required");
        }
    }
}
