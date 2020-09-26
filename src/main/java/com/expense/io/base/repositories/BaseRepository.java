package com.expense.io.base.repositories;

import com.expense.io.base.model.AppBaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T extends AppBaseModel> extends JpaRepository<T, Long>,
                                                                JpaSpecificationExecutor<T> {
}