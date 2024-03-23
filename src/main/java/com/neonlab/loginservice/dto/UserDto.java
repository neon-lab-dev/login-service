package com.neonlab.loginservice.dto;


import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDto {

    private String name;// need to create one utility class for this name in common repo
    private String email;
    private String primaryPhoneNo;// can't be null
    private String secondaryPhoneNo;//optional


    //one more column need to add generic

}
