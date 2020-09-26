package com.expense.io.base.service.nonauth;

import com.expense.io.base.dto.BaseDTO;
import com.expense.io.base.mapper.BaseMapper;
import com.expense.io.base.model.AppBaseModel;
import com.expense.io.base.repositories.BaseRepository;
import com.expense.io.renderers.PaginatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityNotFoundException;
import javax.persistence.MappedSuperclass;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@MappedSuperclass
public abstract class ModelServiceImpl<S extends AppBaseModel, T extends BaseDTO, R extends BaseRepository<S>, U extends Specification<S>>
        implements ModelService<S, T, U>,
                   BaseMapper<S, T> {

    private final R repository;

    protected ModelServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
        final List<S> sList = repository.findAll();

        return sList.stream()
                    .map(this::toDto)
                    .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<T> findAll(U specification) {
        final List<S> sList = repository.findAll(specification);

        return sList.stream()
                    .map(this::toDto)
                    .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<T> findAll(Sort sort) {
        final List<S> sList = repository.findAll(sort);

        return sList.stream()
                    .map(this::toDto)
                    .collect(Collectors.toUnmodifiableList());

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
    final public T findById(Long id) {
        final S s = repository.findById(id)
                              .orElseThrow(() -> new EntityNotFoundException("Requested item not found"));

        return toDto(s);
    }

    @Override
    final public S save(T dto) {
        return repository.save(toModel(dto));
    }

    @Override
    final public S updateById(Long id, T dto) {
        final S s = repository.findById(id)
                              .orElseThrow(() -> new EntityNotFoundException("Requested item not found"));

        final S model = toModel(dto);

        model.setId(s.getId());

        return repository.save(s);
    }

    @Override
    final public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
