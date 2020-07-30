package com.expense.io.base.controller;

import com.expense.io.base.dto.BaseUserDTO;
import com.expense.io.base.model.AppBaseUserModel;
import com.expense.io.base.service.auth.ModelAuthService;
import com.expense.io.project.auth.AppUser;
import com.expense.io.project.auth.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

abstract public class BaseAuthController<S extends AppBaseUserModel, T extends BaseUserDTO, E extends ModelAuthService<S, T>>
        implements GenericAuthController<S, T> {
    private final UserRepository repository;
    private final E service;

    public BaseAuthController(UserRepository repository, E service) {
        this.repository = repository;
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Override
    public S create(@Valid @RequestBody T dto, Principal principal) {
        final AppUser user = repository.getByUsername(principal.getName());
        return service.save(dto, user);
    }

    @ResponseStatus(HttpStatus.OK)
//    @GetMapping
    @Override
    public List<T> list(Principal principal) {
        final AppUser user = repository.getByUsername(principal.getName());
        return service.findAll(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}")
    @Override
    public S update(@PathVariable Long id, @Valid @RequestBody T dto, Principal principal) {
        final AppUser user = repository.getByUsername(principal.getName());
        return service.updateById(id, dto, user);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    @Override
    public T retrieve(@PathVariable Long id, Principal principal) {
        final AppUser user = repository.getByUsername(principal.getName());
        return service.findById(id, user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    @Override
    public void destroy(@PathVariable Long id, Principal principal) {
        final AppUser user = repository.getByUsername(principal.getName());
        service.deleteById(id, user);
    }
}
