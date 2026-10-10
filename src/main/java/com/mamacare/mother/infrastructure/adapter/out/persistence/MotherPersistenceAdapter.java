
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

    private final SpringDataMotherRepository springDataRepository;
    private final MotherPersistenceMapper mapper;

    public MotherPersistenceAdapter(
            SpringDataMotherRepository springDataRepository,
            MotherPersistenceMapper mapper
    ) {
        this.springDataRepository = springDataRepository;
        this.mapper = mapper;
    }

    @Override
    public Mother save(Mother mother) {

        MotherJpaEntity entity = mapper.toJpaEntity(mother);

        MotherJpaEntity savedEntity = springDataRepository.save(entity);

        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Mother> findById(MotherId id) {

        return springDataRepository.findById(id.value())
                .map(mapper::toDomain);
    }

    @Override
    public List<Mother> findAll() {

        return springDataRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsById(MotherId id) {

        return springDataRepository.existsById(id.value());
    }
}
