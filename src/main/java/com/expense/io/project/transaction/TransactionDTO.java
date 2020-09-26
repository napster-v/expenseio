package com.expense.io.project.transaction;

import com.expense.io.base.dto.BaseUserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@Data
public class TransactionDTO extends BaseUserDTO {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    @NotBlank
    private String title;

    @NotNull
    private LocalDate date;

    @PositiveOrZero
    @NotNull
    private Integer amount;

    private String description;

    @Transient
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
