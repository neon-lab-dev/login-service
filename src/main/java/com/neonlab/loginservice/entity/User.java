package com.neonlab.loginservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class User extends Generic {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)//logic to generate table id, have some doubt how to create UUId
    private String id;

    @Column(name = "name")// this column definition is optional
    private String name;// need to create one utility class for this name in common repo

    @Column(name = "email")
    private String email;

    @Column(name = "primary_phonenumber")
    private String primaryPhoneNo;// can't be null

    @Column(name = "secondary_phonenumber")
    private String secondaryPhoneNo;//optional


    //one more column need to add generic


}
