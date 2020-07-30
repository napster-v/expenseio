package com.expense.io.project.category;

import com.expense.io.base.service.auth.ModelAuthServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends ModelAuthServiceImpl<Category, CategoryDTO, CategoryRepository> {
    public CategoryService(CategoryRepository repository) {
        super(repository);
    }

    @Override
    public CategoryDTO toDto(Category source) {
        return CategoryMapper.INSTANCE.toDto(source);
    }

    @Override
    public Category toModel(CategoryDTO target) {
        return CategoryMapper.INSTANCE.toModel(target);
    }
}
