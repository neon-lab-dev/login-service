package com.neonlab.loginservice.apis;

import com.neonlab.common.annotations.Loggable;
import com.neonlab.common.dto.ApiOutput;
import com.neonlab.common.dto.PhoneNoVerificationRequest;
import com.neonlab.common.enums.OtpStatus;
import com.neonlab.common.expectations.InvalidInputException;
import com.neonlab.common.expectations.ServerException;
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
public class OtpSendApi {

    private final OtpService otpService;

    public ApiOutput<String> send(PhoneNoVerificationRequest request) throws InvalidInputException {
        validateRequest(request);
        try {
            return new ApiOutput<>(HttpStatus.OK.value(), otpService.send(request), OtpStatus.SENT.name());
        } catch (ServerException e){
            return new ApiOutput<>(HttpStatus.BAD_REQUEST.value(), e.getMessage(), OtpStatus.FAILED.name());
        }
    }

    //ToDO: create these in Abstract Api
    private void validateRequest(PhoneNoVerificationRequest authenticationRequest) throws InvalidInputException {
        if(StringUtil.isNullOrEmpty(authenticationRequest.getVerificationPurpose())){
            throw new InvalidInputException("Purpose is Required");
        }
        if(StringUtil.isNullOrEmpty(authenticationRequest.getPhoneNo())){
            throw new InvalidInputException("Phone is Required");
        }
    }

}
