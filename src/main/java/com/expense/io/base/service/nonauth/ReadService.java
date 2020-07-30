package com.expense.io.base.service.nonauth;


import com.expense.io.base.dto.BaseDTO;
import com.expense.io.base.model.AppBaseModel;

import java.util.List;

public interface ReadService<S extends AppBaseModel, T extends BaseDTO> {
    List<T> findAll();

    T findById(Long id);
}
