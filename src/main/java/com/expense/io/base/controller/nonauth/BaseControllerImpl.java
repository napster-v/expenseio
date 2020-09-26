package com.expense.io.base.controller.nonauth;

import com.expense.io.base.dto.BaseDTO;
import com.expense.io.base.model.AppBaseModel;
import com.expense.io.base.service.nonauth.ModelService;
import com.expense.io.renderers.PaginatedResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.MappedSuperclass;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@MappedSuperclass
public abstract class BaseControllerImpl<S extends AppBaseModel, T extends BaseDTO, E extends ModelService<S, T, U>, U extends Specification<S>>
        implements BaseController<S, T, U> {

    private final E service;

    public BaseControllerImpl(E service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Override
    final public S create(@Valid @RequestBody T dto) {
        return service.save(dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<T> list() {
        return service.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<T> list(String sortBy) {
        Sort sort;

        if (sortBy.contains("-")) {
            sort = Sort.by(sortBy.replace("-", ""))
                       .descending();
        } else {
            sort = Sort.by(sortBy)
                       .ascending();
        }
        return service.findAll(sort);
    }

    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<T> list(U specification) {
        return service.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @Override
    public PaginatedResponse<S> list(int page, int size, String sortBy, U specification, HttpServletRequest request) {
        Pageable pageRequest;

        if (sortBy.contains("-")) {

            pageRequest = PageRequest.of(page - 1,
                                         size,
                                         Sort.by(sortBy.replace("-", ""))
                                             .descending());
        } else {
            pageRequest = PageRequest.of(page - 1,
                                         size,
                                         Sort.by(sortBy)
                                             .ascending());
        }
        return service.findAll(specification, pageRequest, request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}")
    @Override
    final public S update(@PathVariable Long id, @Valid @RequestBody T dto) {
        return service.updateById(id, dto);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    @Override
    final public T retrieve(@PathVariable Long id) {
        return service.findById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    @Override
    final public void destroy(@PathVariable Long id) {
        service.deleteById(id);
    }
}
