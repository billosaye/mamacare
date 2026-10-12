
package com.mamacare.mother.application.dto;

import com.mamacare.mother.domain.entity.Mother;

import java.time.LocalDate;
import java.util.UUID;

public record MotherResponse(
        UUID id,
        String firstName,
        String lastName,
        String phoneNumber,
        LocalDate dateOfBirth
) {

    public static MotherResponse from(Mother mother) {

        return new MotherResponse(
                mother.getId().value(),
                mother.getFirstName(),
                mother.getLastName(),
                mother.getPhoneNumber().value(),
                mother.getDateOfBirth().value()
        );
    }
}
