package com.expense.io.base.controller.auth;

import com.expense.io.base.dto.BaseUserDTO;
import com.expense.io.base.model.AppBaseUserModel;
import com.expense.io.base.service.auth.ModelAuthService;
import com.expense.io.base.specifications.UserSpecification;
import com.expense.io.project.auth.AppUser;
import com.expense.io.project.auth.UserRepository;
import com.expense.io.renderers.PaginatedResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

abstract public class BaseAuthControllerImpl<S extends AppBaseUserModel, T extends BaseUserDTO, E extends ModelAuthService<S, T, U>, U extends UserSpecification<S>>
        implements BaseAuthController<S, T, U> {
    private final UserRepository repository;

    private final E service;

    public BaseAuthControllerImpl(UserRepository repository, E service) {
        this.repository = repository;
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Override
    final public S create(@Valid @RequestBody T dto, Principal principal) {
        return service.save(dto, getUserByUsername(principal));
    }

    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<T> list(Principal principal) {
        return service.findAll(getUserByUsername(principal));
    }

    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<T> list(U specification, String sortBy) {
        Sort sort;

        if (sortBy.contains("-")) {
            sort = Sort.by(sortBy.replace("-", ""))
                       .descending();
        } else {
            sort = Sort.by(sortBy)
                       .ascending();
        }
        return service.findAll(specification, sort);
    }

    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<T> list(U specification) {
        return service.findAll(specification);
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
    final public S update(@PathVariable Long id, @Valid @RequestBody T dto, Principal principal) {
        return service.updateById(id, dto, getUserByUsername(principal));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    @Override
    final public T retrieve(@PathVariable Long id, Principal principal) {
        return service.findById(id, getUserByUsername(principal));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    @Override
    final public void destroy(@PathVariable Long id, Principal principal) {
        service.deleteById(id, getUserByUsername(principal));
    }

    @Override
    final public AppUser getUserByUsername(Principal principal) {
        return repository.getByUsername(principal.getName());
    }
}
