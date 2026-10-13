package com.mamacare.mother.application.mapper;

import com.mamacare.mother.application.dto.MotherResponse;
import com.mamacare.mother.domain.entity.Mother;
import org.springframework.stereotype.Component;

@Component
public class MotherMapper {

    public MotherResponse toResponse(Mother mother) {
        return new MotherResponse(
                mother.getId().value(),
                mother.getFirstName(),
                mother.getLastName(),
                mother.getPhoneNumber().value(),
                mother.getDateOfBirth().value()
        );
    }
}
