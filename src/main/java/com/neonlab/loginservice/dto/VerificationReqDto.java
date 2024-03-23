package com.neonlab.loginservice.dto;

import lombok.Data;

@Data
public class VerificationReqDto extends Authenticationdto{
    private String otp;
}
