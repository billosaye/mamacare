
package com.mamacare.mother.domain.valueobject;

public record PhoneNumber(String value) {

    public PhoneNumber {

        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Phone number cannot be empty"
            );
        }

        value = value.trim();

        if (value.startsWith("0")) {
            value = "+254" + value.substring(1);
        } else if (value.startsWith("254")) {
            value = "+" + value;
        }

        if (!value.matches("\\+254[71]\\d{8}")) {
            throw new IllegalArgumentException(
                    "Invalid Kenyan mobile phone number"
            );
        }
    }
}
  