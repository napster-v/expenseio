package com.expense.io.base.repositories;

import com.expense.io.base.model.AppBaseUserModel;
import com.expense.io.project.auth.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseUserRepository<T extends AppBaseUserModel> extends JpaRepository<T, Long>,
                                                                        JpaSpecificationExecutor<T> {
    List<T> findAllByUser(AppUser user);


    Optional<T> findByUserAndId(AppUser user, Long id);

    @Transactional
    void deleteByUserAndId(AppUser user, Long id);

}