package com.neonlab.loginservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auth_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser extends Generic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "uuid")
    private String UUID;

    @Column(name = "username")//index/ph no/email
    private String userName;

    @Column(name = "auth_type") //index/otp
    private String authType;

    @Column(name = "device_id") //index/otp
    private String deviceId;

    @Column(name = "phone_verified")
    private boolean phoneVerified; //by default false, API code will update it as true after verification

    @Column(name = "email_verified")
    private boolean emailVerified;

    @Column(name = "token")
    private String JWTtoken;

    //generic

}
