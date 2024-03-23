package com.neonlab.loginservice.controller;

import com.neonlab.loginservice.apis.OtpApi;
import com.neonlab.loginservice.dto.AuthUserDto;
import com.neonlab.loginservice.dto.Authenticationdto;
import com.neonlab.loginservice.dto.VerificationReqDto;
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
public class AuthenticationController {

    private final OtpApi otpApi;

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody Authenticationdto authenticationdto) {
        return new ResponseEntity<>(otpApi.sendOtp(authenticationdto), HttpStatus.OK);
    }

    @PostMapping("/verify-account")
    public ResponseEntity<AuthUserDto> verifyAccount(@RequestBody VerificationReqDto verificationReqDto) {
        return new ResponseEntity<>(otpApi.verifyOtp(verificationReqDto), HttpStatus.OK);
    }


    //To Modify
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Authenticationdto loginDto) {
        //return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
