package com.expense.io.project.category;

import com.expense.io.base.dto.BaseUserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryDTO extends BaseUserDTO {
    @NotEmpty
    @NotBlank
    private String title;

    @NotEmpty
    @NotBlank
    private String colour;
    private String icon;

    @PositiveOrZero
    @NotNull
    private Integer budget;
}
