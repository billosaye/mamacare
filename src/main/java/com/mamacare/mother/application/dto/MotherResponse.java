package com.mamacare.mother.application.dto;

import java.time.LocalDate;
import java.util.UUID;

public record MotherResponse(
        UUID id,
        String firstName,
        String lastName,
        String phoneNumber,
        LocalDate dateOfBirth
) {
}