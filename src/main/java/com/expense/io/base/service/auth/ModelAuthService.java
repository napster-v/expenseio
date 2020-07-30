package com.expense.io.base.service.auth;

import com.expense.io.base.dto.BaseUserDTO;
import com.expense.io.base.model.AppBaseUserModel;

public interface ModelAuthService<S extends AppBaseUserModel, T extends BaseUserDTO> extends ReadAuthService<S, T>,
                                                                                             CreateAuthService<S, T>,
                                                                                             UpdateAuthService<S, T>,
                                                                                             DeleteAuthService<S, T> {
}
