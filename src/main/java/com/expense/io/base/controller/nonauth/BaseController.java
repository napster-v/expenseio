package com.expense.io.base.controller.nonauth;

import com.expense.io.base.dto.BaseDTO;
import com.expense.io.base.model.AppBaseModel;
import com.expense.io.renderers.PaginatedResponse;
import org.springframework.data.jpa.domain.Specification;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface BaseController<S extends AppBaseModel, T extends BaseDTO, U extends Specification<S>> {
    List<T> list();

    List<T> list(String sortBy);

    List<T> list(U specification);

    PaginatedResponse<S> list(int page, int size, String sortBy, U specification, HttpServletRequest request);

    S create(T dto);

    T retrieve(Long id);

    S update(Long id, T dto);

    void destroy(Long id);
}
