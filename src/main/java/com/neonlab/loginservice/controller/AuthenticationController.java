package com.neonlab.loginservice.controller;

import com.neonlab.common.dto.AuthUserDto;
import com.neonlab.common.dto.Authenticationdto;
import com.neonlab.common.dto.VerificationReqDto;
import com.neonlab.loginservice.apis.OtpApi;
import com.neonlab.loginservice.apis.SendOtpApi;
import com.neonlab.loginservice.apis.VerifyOtpApi;
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
        return new ResponseEntity<>(otpApi.send(authenticationdto), HttpStatus.OK);
    }

    @PostMapping("/verify-account")
    public ResponseEntity<AuthUserDto> verifyAccount(@RequestBody VerificationReqDto verificationReqDto) {
        return new ResponseEntity<>(otpApi.verify(verificationReqDto), HttpStatus.OK);
    }


    //To Modify
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Authenticationdto loginDto) {
        //return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
