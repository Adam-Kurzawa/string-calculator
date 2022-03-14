package com.example.precalculation;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerValidatorTest {
    private final IntegerValidator integerValidator = new IntegerValidator();

    @Test
    public void shouldThrowWhenNegativeNumbersOccur() {
        assertThrows(NegativeNumbersNotAllowedException.class, () -> integerValidator.validate(List.of(-2, -1, 0, 1, 2, 3)));
    }
}
