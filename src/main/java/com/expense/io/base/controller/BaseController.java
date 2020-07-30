package com.expense.io.base.controller;

import com.expense.io.base.dto.BaseDTO;
import com.expense.io.base.model.AppBaseModel;
import com.expense.io.base.service.nonauth.ModelService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.MappedSuperclass;
import javax.validation.Valid;
import java.util.List;

@MappedSuperclass
public abstract class BaseController<S extends AppBaseModel, T extends BaseDTO, E extends ModelService<S, T>>
        implements GenericNonAuthController<S, T> {

    private final E service;

    public BaseController(E service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Override
    public S create(@Valid @RequestBody T dto) {
        return service.save(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    @Override
    public List<T> list() {
        return service.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}")
    @Override
    public S update(@PathVariable Long id, @Valid @RequestBody T dto) {
        return service.updateById(id, dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    @Override
    public T retrieve(@PathVariable Long id) {
        return service.findById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    @Override
    public void destroy(@PathVariable Long id) {
        service.deleteById(id);
    }
}
