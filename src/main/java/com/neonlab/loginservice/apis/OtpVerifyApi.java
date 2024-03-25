package com.neonlab.loginservice.apis;

import com.neonlab.common.annotations.Loggable;
import com.neonlab.common.dto.ApiOutput;
import com.neonlab.common.dto.AuthUserDto;
import com.neonlab.common.dto.PhoneNoVerificationRequest;
import com.neonlab.common.dto.VerificationResponse;
import com.neonlab.common.enums.OtpStatus;
import com.neonlab.common.expectations.InvalidInputException;
import com.neonlab.common.expectations.ServerException;
import com.neonlab.common.services.UserService;
import com.neonlab.common.utilities.StringUtil;
import com.neonlab.loginservice.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Loggable
@RequiredArgsConstructor
public class OtpVerifyApi {
    private final OtpService otpService;

    private static final String OTP_VERIFIED = "OTP verified successfully for %s";

    public ApiOutput<VerificationResponse> verify(PhoneNoVerificationRequest phoneNoVerificationRequest) throws InvalidInputException {
        validateRequest(phoneNoVerificationRequest);
        try {
            var retVal = otpService.verify(phoneNoVerificationRequest);
            return new ApiOutput<>(HttpStatus.OK.value(), String.format(OTP_VERIFIED, phoneNoVerificationRequest.getPhoneNo()), retVal);
        } catch (ServerException e) {
            return new ApiOutput<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(),new VerificationResponse(null, OtpStatus.FAILED.name()));
        }
    }

    private void validateRequest(PhoneNoVerificationRequest phoneNoVerificationRequest) throws InvalidInputException {
        if (StringUtil.isNullOrEmpty(phoneNoVerificationRequest.getOtp())) {
            throw new InvalidInputException("Otp is Required");
        }
        if (StringUtil.isNullOrEmpty(phoneNoVerificationRequest.getPurpose())) {
            throw new InvalidInputException("Purpose is Required");
        }
        if (StringUtil.isNullOrEmpty(phoneNoVerificationRequest.getPhoneNo())) {
            throw new InvalidInputException("Phone or Email is Required");
        }
    }
}
