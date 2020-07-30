package com.expense.io.project.transaction;

import com.expense.io.base.service.auth.ModelAuthServiceImpl;
import com.expense.io.project.auth.AppUser;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService extends ModelAuthServiceImpl<Transaction, TransactionDTO, TransactionRepository> {
    private final TransactionRepository repository;

    public TransactionService(TransactionRepository repository) {
        super(repository);
        this.repository = repository;
    }

    @Override
    public TransactionDTO toDto(Transaction source) {
        return TransactionMapper.INSTANCE.toDto(source);
    }

    @Override
    public Transaction toModel(TransactionDTO target) {
        return TransactionMapper.INSTANCE.toModel(target);
    }

    public List<TransactionDTO> findAll(LocalDate startDate, LocalDate endDate, AppUser user) {
        final List<Transaction> transactionList = repository.findByDateBetweenAndUser(startDate, endDate, user);
        return transactionList.stream()
                              .map(this::toDto)
                              .collect(Collectors.toUnmodifiableList());
    }

    public List<TransactionDTO> findAll(TransactionSpecification specification) {
        final List<Transaction> transactionList = repository.findAll(specification);
        return transactionList.stream()
                              .map(this::toDto)
                              .collect(Collectors.toUnmodifiableList());
    }
}
