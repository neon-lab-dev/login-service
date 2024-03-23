package com.neonlab.loginservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Address extends Generic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "uuid")
    private String UUID;

    @Column(name = "landmark")
    private String landMark;

    @Column(name = "address_line1")
    private String addressLine1;  // can't be null

    @Column(name = "city")
    private String city; // UI dropdown list

    @Column(name = "state")
    private String state;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "address_name")
    private String addressName; // can't be null

    //generic

}
