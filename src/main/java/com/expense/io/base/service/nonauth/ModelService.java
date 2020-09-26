package com.expense.io.base.service.nonauth;

import com.expense.io.base.dto.BaseDTO;
import com.expense.io.base.model.AppBaseModel;
import com.expense.io.renderers.PaginatedResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ModelService<S extends AppBaseModel, T extends BaseDTO, P extends Specification<S>> {
    S save(T dto);

    void deleteById(Long id);

    List<T> findAll();

    List<T> findAll(Sort sort);

    PaginatedResponse<S> findAll(P specification, Pageable pageable, HttpServletRequest request);

    List<T> findAll(P specification);

    T findById(Long id);

    S updateById(Long id, T dto);
}
