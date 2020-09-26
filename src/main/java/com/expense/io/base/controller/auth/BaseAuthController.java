package com.expense.io.base.controller.auth;

import com.expense.io.base.dto.BaseDTO;
import com.expense.io.base.model.AppBaseUserModel;
import com.expense.io.base.specifications.UserSpecification;
import com.expense.io.project.auth.AppUser;
import com.expense.io.renderers.PaginatedResponse;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

public interface BaseAuthController<S extends AppBaseUserModel, T extends BaseDTO, U extends UserSpecification<S>> {
    List<T> list(Principal principal);

    List<T> list(U specification, String sortBy);

    List<T> list(U specification);

    PaginatedResponse<S> list(int page, int size, String sortBy, U specification, HttpServletRequest request);

    T retrieve(Long id, Principal principal);

    S update(Long id, T dto, Principal principal);

    S create(T dto, Principal principal);

    void destroy(Long id, Principal principal);

    AppUser getUserByUsername(Principal principal);
}
