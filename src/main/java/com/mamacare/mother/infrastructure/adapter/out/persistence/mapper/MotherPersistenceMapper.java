package com.mamacare.mother.infrastructure.adapter.out.persistence.mapper;

import com.mamacare.mother.domain.entity.Mother;
import com.mamacare.mother.domain.valueobject.DateOfBirth;
import com.mamacare.mother.domain.valueobject.MotherId;
import com.mamacare.mother.domain.valueobject.PhoneNumber;
import com.mamacare.mother.infrastructure.adapter.out.persistence.entity.MotherJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class MotherPersistenceMapper {

    public MotherJpaEntity toEntity(Mother mother) {
        return new MotherJpaEntity(
                mother.getId() == null ? null : mother.getId().value(),
                mother.getFirstName(),
                mother.getLastName(),
                mother.getPhoneNumber().value(),
                mother.getDateOfBirth().value()
        );
    }

    public Mother toDomain(MotherJpaEntity entity) {
        return Mother.reconstitute(
                new MotherId(entity.getId()),
                entity.getFirstName(),
                entity.getLastName(),
                new PhoneNumber(entity.getPhoneNumber()),
                new DateOfBirth(entity.getDateOfBirth())
        );
    }
}