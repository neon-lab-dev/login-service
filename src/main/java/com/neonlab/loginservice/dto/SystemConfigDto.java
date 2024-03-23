package com.neonlab.loginservice.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class SystemConfigDto {

    private String key;
    private String value;

}
