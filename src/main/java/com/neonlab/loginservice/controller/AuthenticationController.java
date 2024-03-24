package com.neonlab.loginservice.controller;

import com.neonlab.common.annotations.Loggable;
import com.neonlab.common.dto.ApiOutput;
import com.neonlab.common.dto.AuthenticationRequest;
import com.neonlab.common.dto.PhoneNoVerificationRequest;
import com.neonlab.common.expectations.InvalidInputException;
import com.neonlab.loginservice.apis.OtpSendApi;
import com.neonlab.loginservice.apis.OtpVerifyApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/customer")
@RequiredArgsConstructor
@Loggable
public class AuthenticationController {

    private final OtpSendApi otpSendApi;
    private final OtpVerifyApi otpVerifyApi;


    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody PhoneNoVerificationRequest request) throws InvalidInputException {
        return new ResponseEntity<>(otpSendApi.send(request), HttpStatus.OK);
    }

    @PostMapping("/verify-otp")
    public ApiOutput<?> verifyAccount(@RequestBody PhoneNoVerificationRequest phoneNoVerificationRequest)
            throws InvalidInputException {
        return otpVerifyApi.verify(phoneNoVerificationRequest);
    }


    //To Modify
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest loginDto) {
        //return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
