package com.expense.io.base.controller;

import com.expense.io.base.dto.BaseDTO;
import com.expense.io.base.model.AppBaseModel;

import java.security.Principal;
import java.util.List;

public interface GenericAuthController<S extends AppBaseModel, T extends BaseDTO> {
    List<T> list(Principal principal);

    T retrieve(Long id, Principal principal);

    S update(Long id, T dto, Principal principal);

    S create(T dto, Principal principal);

    void destroy(Long id, Principal principal);


}
