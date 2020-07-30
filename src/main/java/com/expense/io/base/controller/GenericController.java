package com.expense.io.base.controller;


import com.expense.io.base.dto.BaseDTO;
import com.expense.io.base.model.AppBaseModel;

public interface GenericController<S extends AppBaseModel, T extends BaseDTO> {

    S update(Long id, T dto);

    T retrieve(Long id);

    void destroy(Long id);
}
