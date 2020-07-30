package com.expense.io.project.transaction;

import com.expense.io.base.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper extends BaseMapper<Transaction, TransactionDTO> {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);


    @Mapping(source = "categoryId", target = "category.id")
    @Override
    Transaction toModel(TransactionDTO target);
}
