package com.neonlab.loginservice.controller;

import com.neonlab.loginservice.apis.SignUPInterface;
import com.neonlab.loginservice.apis.SignupApi;
import com.neonlab.loginservice.dto.Authenticationdto;
import com.neonlab.loginservice.dto.VerificationReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping

public class UserController {

    @Autowired
    private SignUPInterface signUPInterface;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Authenticationdto signupDto) {
        return new ResponseEntity<>(signUPInterface.authenticate(signupDto), HttpStatus.OK);
    }

    @PutMapping("/verify-account")
    public ResponseEntity<String> verifyAccount(@RequestBody VerificationReqDto verificationReqDto) {
        //return new ResponseEntity<>(userService.verifyAccount(verificationReqDto), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/login")
    public ResponseEntity<String> login(@RequestBody Authenticationdto loginDto) {
        //return new ResponseEntity<>(userService.login(loginDto), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
