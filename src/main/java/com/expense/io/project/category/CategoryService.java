package com.expense.io.project.category;

import com.expense.io.base.service.auth.ModelAuthServiceImpl;
import com.expense.io.project.auth.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService
        extends ModelAuthServiceImpl<Category, CategoryDTO, CategoryRepository, CategorySpecification> {
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

    //    @Cacheable(cacheNames = "categories", key = "#root.method.name")
    @Override
    public List<CategoryDTO> findAll(AppUser user) {
        return super.findAll(user);
    }
}
