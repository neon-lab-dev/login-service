package com.neonlab.loginservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/v1/customer")
public class AuthenticationController {


    @PostMapping("/otp/send")
    public ResponseEntity<?> sendOtp(){
        return ResponseEntity.ok("Success");
    }


}
