package com.expense.io.base.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public class AppBaseTitleUserModel extends AppBaseUserModel {
    @NotNull
    private String title;
}
