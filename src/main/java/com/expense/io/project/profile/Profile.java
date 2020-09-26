package com.expense.io.project.profile;

import com.expense.io.base.model.AppBaseModel;
import com.expense.io.project.auth.AppUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * Monthly expense budget and income set by the user.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Profile extends AppBaseModel {

    @PositiveOrZero
    @NotNull
    private int budget;

    @PositiveOrZero
    @NotNull
    private int income;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private AppUser user;
}
