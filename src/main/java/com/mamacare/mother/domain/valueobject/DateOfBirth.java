
package com.mamacare.mother.domain.valueobject;

import java.time.LocalDate;

public record DateOfBirth(LocalDate value) {

    public DateOfBirth {

        if (value == null) {
            throw new IllegalArgumentException(
                    "Date of birth cannot be null"
            );
        }

        if (!value.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Date of birth must be before today"
            );
        }
    }
}
