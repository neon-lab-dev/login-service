/*package com.neonlab.loginservice.controller;


import com.neonlab.loginservice.entity.AuthUser;
import com.neonlab.loginservice.entity.Otp;
import com.neonlab.loginservice.repository.AuthUserRepository;
import com.neonlab.loginservice.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {

    @Autowired
    AuthUserRepository authUserRepository;
    @Autowired OtpRepository otpRepository;

    @PostMapping("/test")
    public String test(){
        AuthUser authUser = new AuthUser();
        authUser.setUserName("bilkis96");
        authUser.setAuthType("otp");
        authUser.setUUID(UUID.randomUUID().toString());
        authUser.setCreatedAt("2023-09-09");
        authUser.setModifiedAt("2025-10-31");
        authUserRepository.save(authUser);
        return "OK";
    }

    @PostMapping("/testotp")
    public String otp(){
        Otp otp = new Otp();
        otp.setServiceProvider("interack");
        otp.setOtp("12");
        otp.setCreatedAt("2024-01-21");
        otp.setModifiedAt("2024-01-31");
        otpRepository.save(otp);
        return "OK";
    }

}*/
