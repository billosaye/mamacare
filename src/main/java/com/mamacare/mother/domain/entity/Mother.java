
package com.mamacare.mother.domain.entity;

import com.mamacare.mother.domain.valueobject.MotherId;
import com.mamacare.mother.domain.valueobject.PhoneNumber;
import com.mamacare.mother.domain.valueobject.DateOfBirth;

public class Mother {

    private MotherId id;
    private String firstName;
    private String lastName;
    private PhoneNumber phoneNumber;
    private DateOfBirth dateOfBirth;

    // Constructor for a new Mother (no database ID yet)
    public Mother(
            String firstName,
            String lastName,
            PhoneNumber phoneNumber,
            DateOfBirth dateOfBirth
    ) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException(
                    "First name cannot be blank"
            );
        }

        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException(
                    "Last name cannot be blank"
            );
        }

        if (phoneNumber == null) {
            throw new IllegalArgumentException(
                    "Phone number cannot be null"
            );
        }

        if (dateOfBirth == null) {
            throw new IllegalArgumentException(
                    "Date of birth cannot be null"
            );
        }

        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    // Reconstruct an existing Mother from persistence
    public static Mother reconstitute(
            MotherId id,
            String firstName,
            String lastName,
            PhoneNumber phoneNumber,
            DateOfBirth dateOfBirth
    ) {
        if (id == null) {
            throw new IllegalArgumentException(
                    "Mother ID cannot be null when reconstituting"
            );
        }

        Mother mother = new Mother(
                firstName,
                lastName,
                phoneNumber,
                dateOfBirth
        );

        mother.id = id;

        return mother;
    }

    // Assign a database-generated ID once
    public void assignId(MotherId id) {
        if (id == null) {
            throw new IllegalArgumentException(
                    "Mother ID cannot be null"
            );
        }

        if (this.id != null) {
            throw new IllegalStateException(
                    "Mother ID has already been assigned"
            );
        }

        this.id = id;
    }

    // Controlled updates
    public void updateFirstName(String firstName) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException(
                    "First name cannot be blank"
            );
        }

        this.firstName = firstName.trim();
    }

    public void updateLastName(String lastName) {
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException(
                    "Last name cannot be blank"
            );
        }

        this.lastName = lastName.trim();
    }

    public void updatePhoneNumber(PhoneNumber phoneNumber) {
        if (phoneNumber == null) {
            throw new IllegalArgumentException(
                    "Phone number cannot be null"
            );
        }

        this.phoneNumber = phoneNumber;
    }

    // Read-only access to fields
    public MotherId getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public DateOfBirth getDateOfBirth() {
        return dateOfBirth;
    }
}
