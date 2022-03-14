package com.example.precalculation;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerFilterTest {
    private final IntegerFilter integerFilter = new IntegerFilter();

    @Test
    public void shouldFilterOutNumbersHigherThanThousand() {
        var result = integerFilter.filter(List.of(1, 2, 3, 1004));
        assertEquals(List.of(1, 2, 3), result);
    }
}
