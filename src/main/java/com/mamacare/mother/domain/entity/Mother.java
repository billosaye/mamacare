package com.mamacare.mother.domain.entity;

import com.mamacare.mother.domain.valueobject.MotherId;
import com.mamacare.mother.domain.valueobject.PhoneNumber;
import com.mamacare.mother.domain.valueobject.DateOfBirth;

import lombok.Getter;

@Getter
public class Mother {

    private final MotherId id;
    private String firstName;
    private String lastName;
    private PhoneNumber phoneNumber;
    private DateOfBirth dateOfBirth;

    // Private constructor
    private Mother(
            MotherId id,
            String firstName,
            String lastName,
            PhoneNumber phoneNumber,
            DateOfBirth dateOfBirth
    ) {
        this.id = id;
        this.firstName = validName(firstName, "First name");
        this.lastName = validName(lastName, "Last name");
        this.phoneNumber = required(phoneNumber, "Phone number");
        this.dateOfBirth = required(dateOfBirth, "Date of birth");
    }

    // Validate names
    private static String validName(String name, String label) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException(
                    label + " cannot be blank"
            );
        }

        return name.trim();
    }

    // Validate required values
    private static <T> T required(T value, String label) {

        if (value == null) {
            throw new IllegalArgumentException(
                    label + " is required"
            );
        }

        return value;
    }

    // Register a new mother
    public static Mother register(
            String firstName,
            String lastName,
            PhoneNumber phoneNumber,
            DateOfBirth dateOfBirth
    ) {
        return new Mother(
                null,
                firstName,
                lastName,
                phoneNumber,
                dateOfBirth
        );
    }

    // Reconstruct an existing mother
    public static Mother reconstitute(
            MotherId id,
            String firstName,
            String lastName,
            PhoneNumber phoneNumber,
            DateOfBirth dateOfBirth
    ) {
        return new Mother(
                required(id, "Mother ID"),
                firstName,
                lastName,
                phoneNumber,
                dateOfBirth
        );
    }

    // Update mother's name
    public void updateName(String firstName, String lastName) {

        String validFirst = validName(firstName, "First name");
        String validLast = validName(lastName, "Last name");

        this.firstName = validFirst;
        this.lastName = validLast;
    }

    // Update mother's phone number
    public void updatePhoneNumber(PhoneNumber phoneNumber) {

        this.phoneNumber = required(
                phoneNumber,
                "Phone number"
        );
    }

    // Correct date of birth
    public void correctDateOfBirth(DateOfBirth dateOfBirth) {

        this.dateOfBirth = required(
                dateOfBirth,
                "Date of birth"
        );
    }
}

