package com.expense.io.base.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseTitleDTO extends BaseDTO {
    private String title;
}
