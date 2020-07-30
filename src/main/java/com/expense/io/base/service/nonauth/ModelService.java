package com.expense.io.base.service.nonauth;

import com.expense.io.base.dto.BaseDTO;
import com.expense.io.base.model.AppBaseModel;

public interface ModelService<S extends AppBaseModel, T extends BaseDTO> extends ReadService<S, T>,
                                                                                 CreateService<S, T>,
                                                                                 UpdateService<S, T>,
                                                                                 DeleteService<S, T> {
}
