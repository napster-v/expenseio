package com.expense.io.project.category;

import com.expense.io.base.dto.BaseUserDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CategoryDTO extends BaseUserDTO {
    private String title;
    private String colour;
    private String icon;
    private int budget;

}
