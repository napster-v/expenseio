package com.expense.io.base.dto;

import com.expense.io.project.auth.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseUserDTO extends BaseDTO {
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
