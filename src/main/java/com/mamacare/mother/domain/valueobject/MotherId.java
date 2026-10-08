
package com.mamacare.mother.domain.valueobject;

public record MotherId(Long value) {

    public MotherId {
        if (value == null || value <= 0) {
            throw new IllegalArgumentException(
                    "Mother ID must be a positive number"
            );
        }
    }
}
