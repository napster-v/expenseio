package com.expense.io.project.category;

import com.expense.io.base.model.AppBaseUserModel;
import com.expense.io.project.auth.AppUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @NotNull annotaion added for database level NOT NULL.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Category extends AppBaseUserModel {
    @NotEmpty
    @NotBlank
    @NotNull
    private String title;

    @NotEmpty
    @NotBlank
    @NotNull
    private String colour;

    private String icon;

    @NotNull
    private int budget;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser user;
}
