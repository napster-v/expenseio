package com.expense.io.base.service.auth;

import com.expense.io.base.dto.BaseUserDTO;
import com.expense.io.base.mapper.BaseMapper;
import com.expense.io.base.model.AppBaseUserModel;
import com.expense.io.base.repositories.BaseUserRepository;
import com.expense.io.project.auth.AppUser;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

abstract public class ModelAuthServiceImpl<S extends AppBaseUserModel, T extends BaseUserDTO, R extends BaseUserRepository<S>>
        implements ModelAuthService<S, T>,
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
        final Optional<S> s = repository.findByUserAndId(user, id);

        if (s.isPresent()) {
            return toDto(s.get());
        } else throw new EntityNotFoundException("Requested item not found");
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

        final Optional<S> s = repository.findById(id);

        if (s.isPresent()) {
            final S model = toModel(dto);

            model.setId(s.get()
                         .getId());

            return repository.save(model);
        } else throw new EntityNotFoundException("Requested item not found");
    }

    @Override
    public void deleteById(Long id, AppUser user) {
        repository.deleteByUserAndId(user, id);
    }
}
