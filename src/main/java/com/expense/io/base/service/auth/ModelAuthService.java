package com.expense.io.base.service.auth;

import com.expense.io.base.dto.BaseUserDTO;
import com.expense.io.base.model.AppBaseUserModel;
import com.expense.io.project.auth.AppUser;
import com.expense.io.renderers.PaginatedResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ModelAuthService<S extends AppBaseUserModel, T extends BaseUserDTO, U extends Specification<S>> {
    List<T> findAll(AppUser user);

    List<T> findAll(U specification, Sort sort);

    PaginatedResponse<S> findAll(U specification, Pageable pageable, HttpServletRequest request);

    List<T> findAll(U specification);

    T findById(Long id, AppUser user);

    S save(T dto, AppUser user);

    S updateById(Long id, T dto, AppUser user);

    void deleteById(Long id, AppUser user);
}
