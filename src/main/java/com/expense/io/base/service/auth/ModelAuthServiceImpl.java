package com.expense.io.base.service.auth;

import com.expense.io.base.dto.BaseUserDTO;
import com.expense.io.base.mapper.BaseMapper;
import com.expense.io.base.model.AppBaseUserModel;
import com.expense.io.base.repositories.BaseUserRepository;
import com.expense.io.project.auth.AppUser;
import com.expense.io.renderers.PaginatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

abstract public class ModelAuthServiceImpl<S extends AppBaseUserModel, T extends BaseUserDTO, R extends BaseUserRepository<S>, U extends Specification<S>>
        implements ModelAuthService<S, T, U>,
                   BaseMapper<S, T> {
    private final R repository;

    public ModelAuthServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll(AppUser user) {
        final List<S> sList = repository.findAllByUser(user);

        return sList.stream()
                    .map(this::toDto)
                    .collect(Collectors.toUnmodifiableList());
    }


    @Override
    public T findById(Long id, AppUser user) {
        final S s = repository.findByUserAndId(user, id)
                              .orElseThrow(() -> new EntityNotFoundException("Requested item not found"));

        return toDto(s);

    }

    @Override
    public S save(T dto, AppUser user) {
        dto.setUser(user);

        final S model = toModel(dto);

        return repository.save(model);
    }

    @Override
    public S updateById(Long id, T dto, AppUser user) {
        dto.setUser(user);

        final S s = repository.findByUserAndId(user, id)
                              .orElseThrow(() -> new EntityNotFoundException("Requested item not found"));

        final S model = toModel(dto);

        model.setId(s.getId());

        return repository.save(s);
    }

    @Override
    public void deleteById(Long id, AppUser user) {
        repository.deleteByUserAndId(user, id);
    }

    @Override
    public PaginatedResponse<S> findAll(U specification, Pageable pageable, HttpServletRequest request) {
        final Page<S> all = repository.findAll(specification, pageable);

        final List<T> data = all.getContent()
                                .stream()
                                .map(this::toDto)
                                .collect(Collectors.toUnmodifiableList());

        return new PaginatedResponse<>(all, request, data);
    }

    @Override
    public List<T> findAll(U specification) {
        final List<S> sList = repository.findAll(specification);

        return sList.stream()
                    .map(this::toDto)
                    .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<T> findAll(U specification, Sort sort) {
        final List<S> sList = repository.findAll(specification, sort);

        return sList.stream()
                    .map(this::toDto)
                    .collect(Collectors.toUnmodifiableList());
    }
}
