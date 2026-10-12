package com.mamacare.mother.domain.valueobject;

import java.util.UUID;

public record MotherId(UUID value) {

    public MotherId {
        if (value == null) {
            throw new IllegalArgumentException(
                    "Mother ID cannot be null"
            );
        }
    }
}