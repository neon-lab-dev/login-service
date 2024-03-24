package com.neonlab.loginservice.apis;

import com.neonlab.common.annotations.Loggable;
import com.neonlab.common.dto.Authenticationdto;
import com.neonlab.common.expectations.InvalidInputException;
import com.neonlab.common.utilities.StringUtil;
import com.neonlab.loginservice.service.SendOtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@Loggable
@RequiredArgsConstructor
public class OtpSendApi {

    private final SendOtpService sendOtpService;

    public String send(Authenticationdto authenticationdto) throws InvalidInputException {
        validateRequest(authenticationdto);
        return sendOtpService.send(authenticationdto);
    }

    //ToDO: create these in Abstract Api
    private void validateRequest(Authenticationdto authenticationdto) throws InvalidInputException {
        if(StringUtil.isNullOrEmpty(authenticationdto.getVerificationPurpose())){
            throw new InvalidInputException("Purpose is Required");
        }
        if(StringUtil.isNullOrEmpty(authenticationdto.getPhoneNo()) && StringUtil.isNullOrEmpty(authenticationdto.getEmail())){
            throw new InvalidInputException("Phone or Email is Required");
        }
    }

}
