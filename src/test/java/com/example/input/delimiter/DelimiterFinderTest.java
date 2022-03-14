package com.example.input.delimiter;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DelimiterFinderTest {
    private final DelimiterFinder delimiterFinder = new DelimiterFinder();

    @Test
    public void shouldFindPercentSignAsDelimiter() {
        var result = delimiterFinder.findDelimitersOrUseDefault("//[%]1,2,3");
        var expected = new Delimiters(List.of("%"), 5);
        assertEquals(expected, result);
    }

    @Test
    public void shouldFindPercentSignAndDollarSignAsDelimiters() {
        var result = delimiterFinder.findDelimitersOrUseDefault("//[%][$]1,2,3");
        var expected = new Delimiters(List.of("%", "$"), 8);
        assertEquals(expected, result);
    }

    @Test
    public void shouldFindDelimitersOfMultipleCharsLength() {
        var result = delimiterFinder.findDelimitersOrUseDefault("//[****]1,2,3");
        var expected = new Delimiters(List.of("****"), 8);
        assertEquals(expected, result);
    }

    @Test
    public void shouldFindTextDelimiters() {
        var result = delimiterFinder.findDelimitersOrUseDefault("//[abc]1,2,3");
        var expected = new Delimiters(List.of("abc"), 7);
        assertEquals(expected, result);
    }

    @Test
    public void shouldUseDefaultDelimitersWhenLineIsAbsent() {
        var result = delimiterFinder.findDelimitersOrUseDefault("1,2,3,4");
        var expected = new Delimiters(List.of(",", "\n"), 0);
        assertEquals(expected, result);
    }

    @Test
    public void shouldUseDefaultDelimitersWhenLineIsEmpty() {
        var result = delimiterFinder.findDelimitersOrUseDefault("//1,2,3,4");
        var expected = new Delimiters(List.of(",", "\n"), 2);
        assertEquals(expected, result);
    }
}
