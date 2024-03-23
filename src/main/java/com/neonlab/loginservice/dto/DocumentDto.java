package com.neonlab.loginservice.dto;


import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DocumentDto {

    private String UUID;
    private String documentName; // != null
    private String MIMEtype;
    private String base64; // standard
    private String docIdentifier;
    private String entityName;

}
