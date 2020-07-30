package com.expense.io.project.category;

import com.expense.io.base.controller.BaseAuthController;
import com.expense.io.project.auth.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RequestMapping("category")
@RestController
public class CategoryController extends BaseAuthController<Category, CategoryDTO, CategoryService> {
    public CategoryController(UserRepository repository,
                              CategoryService service) {
        super(repository, service);
    }

    @GetMapping
    @Override
    public List<CategoryDTO> list(Principal principal) {
        return super.list(principal);
    }
}
