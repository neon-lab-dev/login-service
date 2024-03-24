package com.neonlab.loginservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "otp", indexes = {
        @Index(name = "idx_communicated_to", columnList = "communicated_to"),
        @Index(name = "idx_status", columnList = "status")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Otp extends Generic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "communicated_to")
    private String communicatedTo;

    @Column(name = "verification_mode")
    private String verificationMode;

    @Column(name = "otp")
    private String otp;

    @Column(name = "service_provider")
    private String serviceProvider;

    @Column(name = "status")
    private String status;

    @Column(name = "expiry_time")
    private Date expiryTime;

    @Column(name = "purpose")
    private String purpose;


}
