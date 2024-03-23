package com.neonlab.loginservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class Generic {

    @Column(name = "created_at")
    private String createdAt; // != null

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_at")
    private String modifiedAt;  //!= null

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "deleted")
    private boolean deleted;


}
