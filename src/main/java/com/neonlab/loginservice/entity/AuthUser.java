package com.neonlab.loginservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "auth_user", indexes = {
        @Index(name = "idx_user_id", columnList = "user_id"),
        @Index(name = "idx_user_name", columnList = "user_name"),
        @Index(name = "idx_auth_type", columnList = "auth_type")
})
public class AuthUser extends Generic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")//index/ph no/email
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
