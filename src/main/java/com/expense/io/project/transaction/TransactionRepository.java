package com.expense.io.project.transaction;

import com.expense.io.base.repositories.BaseUserRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionRepository extends BaseUserRepository<Transaction>,
                                               JpaSpecificationExecutor<Transaction> {
}
