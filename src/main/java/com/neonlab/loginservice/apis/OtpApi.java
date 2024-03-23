package com.neonlab.loginservice.apis;

import com.mysql.cj.util.StringUtils;
import com.neonlab.common.dto.AuthUserDto;
import com.neonlab.common.dto.Authenticationdto;
import com.neonlab.common.dto.VerificationReqDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OtpApi {

    private final SendOtpApi sendOtpApi;
    private final VerifyOtpApi verifyOtpApi;

    public String send(Authenticationdto authenticationdto){
        validateRequest(authenticationdto);
        return sendOtpApi.send(authenticationdto);
    }

    public AuthUserDto verify(VerificationReqDto verificationReqDto){
        validateRequest(verificationReqDto);
        return verifyOtpApi.verify(verificationReqDto);
    }

    //ToDO: create these in Abstract Api
    private void validateRequest(Authenticationdto authenticationdto) {
        if(StringUtils.isNullOrEmpty(authenticationdto.getVerificationPurpose())){
            throw new RuntimeException("Purpose is Required");
        }
        if(StringUtils.isNullOrEmpty(authenticationdto.getEmail()) && StringUtils.isNullOrEmpty(authenticationdto.getEmail())){
            throw new RuntimeException("Phone or Email is Required");
        }
    }

    private void validateRequest(VerificationReqDto verificationReqDto) {
        if(StringUtils.isNullOrEmpty(verificationReqDto.getOtp())){
            throw new RuntimeException("Otp is Required");
        }
        if(StringUtils.isNullOrEmpty(verificationReqDto.getVerificationPurpose())){
            throw new RuntimeException("Purpose is Required");
        }
        if(StringUtils.isNullOrEmpty(verificationReqDto.getEmail()) && StringUtils.isNullOrEmpty(verificationReqDto.getEmail())){
            throw new RuntimeException("Phone or Email is Required");
        }
    }

}
