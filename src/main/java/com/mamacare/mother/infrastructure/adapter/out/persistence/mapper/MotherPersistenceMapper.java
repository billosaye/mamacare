package com.mamacare.mother.infrastructure.adapter.out.persistence.mapper;



import com.mamacare.mother.domain.entity.Mother;
import com.mamacare.mother.domain.valueobject.MotherId;
import com.mamacare.mother.domain.valueobject.PhoneNumber;
import com.mamacare.mother.domain.valueobject.DateOfBirth;
import com.mamacare.mother.infrastructure.adapter.out.persistence.entity.MotherJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class MotherPersistenceMapper {

    // Convert Domain Mother to JPA Entity
    public MotherJpaEntity toJpaEntity(Mother mother) {

        if (mother == null) {
            throw new IllegalArgumentException(
                    "Mother cannot be null"
            );
        } 

        Long id = mother.getId() == null
                ? null
                : mother.getId().value();

        return new MotherJpaEntity(
                id,
                mother.getFirstName(),
                mother.getLastName(),
                mother.getPhoneNumber().value(),
                mother.getDateOfBirth().value()
        );
    }

    // Convert JPA Entity to Domain Mother
    public Mother toDomain(MotherJpaEntity entity) {

        if (entity == null) {
            throw new IllegalArgumentException(
                    "Mother JPA entity cannot be null"
            );
        }

        if (entity.getId() == null) {
            throw new IllegalStateException(
                    "Persisted mother must have an ID"
            );
        }

        MotherId motherId = new MotherId(
                entity.getId()
        );

        PhoneNumber phoneNumber = new PhoneNumber(
                entity.getPhoneNumber()
        );

        DateOfBirth dateOfBirth = new DateOfBirth(
                entity.getDateOfBirth()
        );

        return Mother.reconstitute(
                motherId,
                entity.getFirstName(),
                entity.getLastName(),
                phoneNumber,
                dateOfBirth
        );
    }
}
