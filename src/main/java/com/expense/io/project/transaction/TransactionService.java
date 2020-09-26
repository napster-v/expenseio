package com.expense.io.project.transaction;

import com.expense.io.base.service.auth.ModelAuthServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TransactionService
        extends ModelAuthServiceImpl<Transaction, TransactionDTO, TransactionRepository, TransactionSpecification> {

    public TransactionService(TransactionRepository repository) {
        super(repository);
    }

    @Override
    public TransactionDTO toDto(Transaction source) {
        return TransactionMapper.INSTANCE.toDto(source);
    }

    @Override
    public Transaction toModel(TransactionDTO target) {
        return TransactionMapper.INSTANCE.toModel(target);
    }
}
