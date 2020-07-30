package com.expense.io.base.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Data
@MappedSuperclass
public class AppBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonIgnore
    @CreationTimestamp
    private Timestamp created;

    @JsonIgnore
    @UpdateTimestamp
    private Timestamp modified;

    @JsonIgnore
    private boolean deleted = false;
}
