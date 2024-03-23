package com.neonlab.loginservice.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "systemconfig")
@Data
@AllArgsConstructor
@NoArgsConstructor

//common repo

public class SystemConfig extends Generic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;

    //generic

}
