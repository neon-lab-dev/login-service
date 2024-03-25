package com.neonlab.loginservice.controller;

import com.neonlab.common.annotations.Loggable;
import com.neonlab.common.dto.ApiOutput;
import com.neonlab.common.dto.AuthenticationRequest;
import com.neonlab.common.dto.PhoneNoVerificationRequest;
import com.neonlab.common.dto.SignUpRequest;
import com.neonlab.common.expectations.InvalidInputException;
import com.neonlab.common.expectations.ServerException;
import com.neonlab.loginservice.apis.OtpSendApi;
import com.neonlab.loginservice.apis.OtpVerifyApi;
import com.neonlab.loginservice.apis.SignUpApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/customer")
@RequiredArgsConstructor
@Loggable
public class AuthenticationController {

    private final OtpSendApi otpSendApi;
    private final OtpVerifyApi otpVerifyApi;
    private final SignUpApi signUpApi;


    @PostMapping("/send-otp")
    public ApiOutput<?> sendOtp(@RequestBody PhoneNoVerificationRequest request) throws InvalidInputException {
        return otpSendApi.send(request);
    }

    @PostMapping("/verify-otp")
    public ApiOutput<?> verifyAccount(@RequestBody PhoneNoVerificationRequest phoneNoVerificationRequest)
            throws InvalidInputException {
        return otpVerifyApi.verify(phoneNoVerificationRequest);
    }

    @PutMapping("/signup")
    public ApiOutput<?> signup(@RequestBody SignUpRequest request) throws InvalidInputException, ServerException {
        //return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
        return signUpApi.process(request);
    }


    //To Modify
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest loginDto) {
        //return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
