package com.expense.io.project.transaction;

import com.expense.io.base.dto.BaseUserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class TransactionDTO extends BaseUserDTO {
    @NotNull
    private String title;

    @NotNull
    private Date date;


    @PositiveOrZero(message = "Amount should always be a positive number.")
    @NotNull
    private Integer amount;

    private String description;

    @NotNull
    private Integer categoryId;

    @JsonIgnore
    public int getCategoryId() {
        return categoryId;
    }

    @JsonProperty
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
