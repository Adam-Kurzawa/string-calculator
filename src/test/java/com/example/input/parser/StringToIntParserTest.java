package com.example.input.parser;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StringToIntParserTest {
    private final StringToIntParser stringToIntParser = new StringToIntParser();

    @Test
    public void shouldReturnEmptyListForEmptyString() {
        var result = stringToIntParser.parse(List.of());
        assertEquals(List.of(), result);
    }

    @Test
    public void shouldReturnEmptyListForNonNumericString() {
        var result = stringToIntParser.parse(List.of("a", "b", "c"));
        assertEquals(List.of(0, 0, 0), result);
    }

    @Test
    public void shouldReturnListWithoutNonNumericString() {
        var result = stringToIntParser.parse(List.of("a", "b", "c", "1", "293"));
        assertEquals(List.of(0, 0, 0, 1, 293), result);
    }
}
