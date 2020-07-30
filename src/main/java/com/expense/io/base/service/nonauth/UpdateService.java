package com.expense.io.base.service.nonauth;


import com.expense.io.base.dto.BaseDTO;
import com.expense.io.base.model.AppBaseModel;

public interface UpdateService<S extends AppBaseModel, T extends BaseDTO> {
    S updateById(Long id, T dto);
}
