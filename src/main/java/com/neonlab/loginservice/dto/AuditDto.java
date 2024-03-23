package com.neonlab.loginservice.dto;


import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AuditDto {

    private String rawRequest;
    private String rawResponse;
    private String serviceProvider;


}
