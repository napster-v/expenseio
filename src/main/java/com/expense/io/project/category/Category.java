package com.expense.io.project.category;

import com.expense.io.base.model.AppBaseUserModel;
import com.expense.io.project.auth.AppUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Category extends AppBaseUserModel {
    private String title;
    private String colour;
    private String icon;
    private int budget;
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser user;

}
