package com.mamacare.mother.application.dto;

import java.time.LocalDate;

public record CorrectDateOfBirthRequest(
        LocalDate dateOfBirth
) {
}