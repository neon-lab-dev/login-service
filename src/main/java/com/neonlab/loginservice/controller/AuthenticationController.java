package com.neonlab.loginservice.controller;

import com.neonlab.common.annotations.Loggable;
import com.neonlab.common.dto.*;
import com.neonlab.common.expectations.InvalidInputException;
import com.neonlab.common.expectations.ServerException;
import com.neonlab.loginservice.apis.*;
import lombok.RequiredArgsConstructor;
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
    private final LoginStatusApi loginStatusApi;
    private final LoginApi loginApi;


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
        return signUpApi.process(request);
    }

    @PostMapping("/already/logged-in")
    public ApiOutput<?> sendOtp(@RequestBody LoginRequest request) throws InvalidInputException {
        return loginStatusApi.process(request);
    }


    //To Modify
    @PostMapping("/login")
    public ApiOutput<?> login(@RequestBody LoginRequest request) throws InvalidInputException {
        return loginApi.process(request);
    }


}
