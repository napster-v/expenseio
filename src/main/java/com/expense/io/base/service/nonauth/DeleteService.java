package com.expense.io.base.service.nonauth;

import com.expense.io.base.dto.BaseDTO;
import com.expense.io.base.model.AppBaseModel;

public interface DeleteService<S extends AppBaseModel, T extends BaseDTO> {
    void deleteById(Long id);
}
