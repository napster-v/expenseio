package com.expense.io.base.service.auth;

import com.expense.io.base.dto.BaseUserDTO;
import com.expense.io.base.model.AppBaseUserModel;
import com.expense.io.project.auth.AppUser;

import java.util.List;

public interface ReadAuthService<S extends AppBaseUserModel, T extends BaseUserDTO> extends CreateAuthService<S, T> {
    List<T> findAll(AppUser user);

    T findById(Long id, AppUser user);
}
