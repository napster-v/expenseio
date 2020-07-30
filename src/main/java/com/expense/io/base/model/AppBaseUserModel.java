package com.expense.io.base.model;

import com.expense.io.project.auth.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public class AppBaseUserModel extends AppBaseModel {
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser user;

    @JsonIgnore
    public AppUser getUser() {
        return user;
    }

    @JsonProperty
    public void setUser(AppUser user) {
        this.user = user;
    }
}
