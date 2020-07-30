package com.expense.io.project.transaction;

import com.expense.io.base.model.AppBaseTitleUserModel;
import com.expense.io.project.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Transaction extends AppBaseTitleUserModel {
    @NotNull
    private LocalDate date;

    @PositiveOrZero
    @NotNull
    private int amount;

    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @JsonIgnore
    public Category getCategory() {
        return category;
    }

    @JsonProperty
    public void setCategory(Category category) {
        this.category = category;
    }
}
