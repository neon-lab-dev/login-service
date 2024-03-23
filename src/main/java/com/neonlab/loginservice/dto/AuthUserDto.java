package com.neonlab.loginservice.dto;

import lombok.Data;

@Data
public class AuthUserDto {

    private String UUID;
    private String userName; //index/ph no/email
    private String authType; //index/otp
    private String deviceId; //index/otp
    private boolean phoneVerified; //by default false, API code will update it as true after verification
    private boolean emailVerified;
    private String JWTtoken;

}
