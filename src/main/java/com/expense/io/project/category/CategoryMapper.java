package com.expense.io.project.category;

import com.expense.io.base.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper extends BaseMapper<Category, CategoryDTO> {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
}
