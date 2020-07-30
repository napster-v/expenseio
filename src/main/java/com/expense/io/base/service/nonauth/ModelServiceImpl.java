package com.expense.io.base.service.nonauth;

import com.expense.io.base.dto.BaseDTO;
import com.expense.io.base.mapper.BaseMapper;
import com.expense.io.base.model.AppBaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import javax.persistence.MappedSuperclass;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@MappedSuperclass
public abstract class ModelServiceImpl<S extends AppBaseModel, T extends BaseDTO, R extends JpaRepository<S, Long>>
        implements ModelService<S, T>,
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
    public T findById(Long id) {
        final Optional<S> s = repository.findById(id);

        if (s.isPresent()) {
            return toDto(s.get());
        } else throw new EntityNotFoundException("Requested item not found");
    }

    @Override
    public S save(T dto) {
        return repository.save(toModel(dto));
    }

    @Override
    public S updateById(Long id, T dto) {
        final S s = repository.getOne(id);

        final S model = toModel(dto);

        model.setId(s.getId());

        return repository.save(model);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
