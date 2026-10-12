package com.mamacare.mother.application.dto;

import java.time.LocalDate;

public record UpdateMotherRequest(String firstName,
                                  String lastName,
                                  String phoneNumber,
                                  LocalDate dateOfBirth) {
}
