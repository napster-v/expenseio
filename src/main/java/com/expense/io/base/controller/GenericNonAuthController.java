package com.expense.io.base.controller;

import com.expense.io.base.dto.BaseDTO;
import com.expense.io.base.model.AppBaseModel;

import java.util.List;

public interface GenericNonAuthController<S extends AppBaseModel, T extends BaseDTO> {
    S create(T dto);

    List<T> list();

    T retrieve(Long id);

    S update(Long id, T dto);

    void destroy(Long id);
}
