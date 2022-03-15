package com.example.precalculation;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerValidatorTest {
    private final IntegerValidator integerValidator = new IntegerValidator();

    @Test
    public void shouldThrowWhenNegativeNumbersOccur() {
        var exception = assertThrows(
                NegativeNumbersNotAllowedException.class,
                () -> integerValidator.validate(List.of(-2, -1, 0, 1, 2, 3, -4, 5, -6))
        );

        assertEquals(exception.getMessage(), "Negative numbers [-2, -1, -4, -6] were passed.");
    }
}
