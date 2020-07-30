package com.expense.io.base.service.auth;

import com.expense.io.base.dto.BaseUserDTO;
import com.expense.io.base.model.AppBaseUserModel;
import com.expense.io.project.auth.AppUser;

public interface CreateAuthService<S extends AppBaseUserModel, T extends BaseUserDTO> {
    S save(T dto, AppUser user);
}
