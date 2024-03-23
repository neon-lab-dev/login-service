package com.neonlab.loginservice.apis;

import com.mysql.cj.util.StringUtils;
import com.neonlab.common.dto.Authenticationdto;
import com.neonlab.loginservice.service.SendOtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OtpSendApi {

    private final SendOtpService sendOtpService;

    public String send(Authenticationdto authenticationdto){
        validateRequest(authenticationdto);
        return sendOtpService.send(authenticationdto);
    }

    //ToDO: create these in Abstract Api
    private void validateRequest(Authenticationdto authenticationdto) {
        if(StringUtils.isNullOrEmpty(authenticationdto.getVerificationPurpose())){
            throw new RuntimeException("Purpose is Required");
        }
        if(StringUtils.isNullOrEmpty(authenticationdto.getPhoneNo()) && StringUtils.isNullOrEmpty(authenticationdto.getEmail())){
            throw new RuntimeException("Phone or Email is Required");
        }
    }

}
