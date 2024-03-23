package com.neonlab.loginservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
//common repo
@Table(name = "document", indexes = {
        @Index(name = "idx_user_id", columnList = "user_id")
})
public class Document extends Generic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "document_name")
    private String documentName; // != null

    @Column(name = "mime_type")
    private String MIMEtype;

    @Column(name = "base64")
    private String base64; // standard

    @Column(name = "document_identifier")
    private String docIdentifier;

    @Column(name = "enity_name")
    private String entityName;

    //generic

}
