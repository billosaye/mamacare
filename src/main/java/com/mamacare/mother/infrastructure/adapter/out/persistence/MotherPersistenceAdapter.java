package com.mamacare.mother.infrastructure.adapter.out.persistence;

import com.mamacare.mother.domain.entity.Mother;
import com.mamacare.mother.domain.repository.MotherRepository;
import com.mamacare.mother.domain.valueobject.MotherId;
import com.mamacare.mother.infrastructure.adapter.out.persistence.entity.MotherJpaEntity;
import com.mamacare.mother.infrastructure.adapter.out.persistence.mapper.MotherPersistenceMapper;
import com.mamacare.mother.infrastructure.adapter.out.persistence.repository.SpringDataMotherRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MotherPersistenceAdapter implements MotherRepository {

    private final SpringDataMotherRepository springDataMotherRepository;
    private final MotherPersistenceMapper mapper;

    public MotherPersistenceAdapter(
            SpringDataMotherRepository springDataMotherRepository,
            MotherPersistenceMapper mapper
    ) {
        this.springDataMotherRepository = springDataMotherRepository;
        this.mapper = mapper;
    }

    @Override
    public Mother save(Mother mother) {
        MotherJpaEntity entity = mapper.toEntity(mother);
        MotherJpaEntity savedEntity = springDataMotherRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Mother> findById(MotherId id) {
        return springDataMotherRepository.findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public List<Mother> findAll() {
        return springDataMotherRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsById(MotherId id) {
        return springDataMotherRepository.existsById(id.value());
    }
}