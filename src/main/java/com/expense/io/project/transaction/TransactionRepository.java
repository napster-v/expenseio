package com.expense.io.project.transaction;

import com.expense.io.base.repositories.BaseUserRepository;
import com.expense.io.project.auth.AppUser;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends BaseUserRepository<Transaction>,
                                               JpaSpecificationExecutor<Transaction> {
    List<Transaction> findByDateBetweenAndUser(LocalDate startDate, LocalDate endDate, AppUser user);

    List<Transaction> findAllByDateBetweenAndUser(LocalDate startDate, LocalDate endDate, AppUser user);
}
