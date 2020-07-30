package com.expense.io.base.service.auth;

import com.expense.io.base.dto.BaseUserDTO;
import com.expense.io.base.model.AppBaseUserModel;
import com.expense.io.project.auth.AppUser;

public interface UpdateAuthService<S extends AppBaseUserModel, T extends BaseUserDTO> {
    S updateById(Long id, T dto, AppUser user);
}
