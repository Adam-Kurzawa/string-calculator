package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DelimiterFinderTest {
    private final DelimiterFinder delimiterFinder = new DelimiterFinder();

    @Test
    public void shouldFindPercentSignAsDelimiter() {
        var result = delimiterFinder.findDelimitersOrUseDefault("//[%]1,2,3");
        var expected = new String[] { "%" };
        assertArrayEquals(result, expected);
    }

    @Test
    public void shouldFindPercentSignAndDollarSignAsDelimiters() {
        var result = delimiterFinder.findDelimitersOrUseDefault("//[%][$]1,2,3");
        var expected = new String[] { "%", "$" };
        assertArrayEquals(result, expected);
    }

    @Test
    public void shouldFindDelimitersOfMultipleCharsLength() {
        var result = delimiterFinder.findDelimitersOrUseDefault("//[****]1,2,3");
        var expected = new String[] { "****" };
        assertArrayEquals(result, expected);
    }

    @Test
    public void shouldFindTextDelimiters() {
        var result = delimiterFinder.findDelimitersOrUseDefault("//[abc]1,2,3");
        var expected = new String[] { "abc" };
        assertArrayEquals(result, expected);
    }

    @Test
    public void shouldUseDefaultDelimitersWhenLineIsAbsent() {
        var result = delimiterFinder.findDelimitersOrUseDefault("1,2,3,4");
        var expected = new String[] { ",", "\n" };
        assertArrayEquals(result, expected);
    }

    @Test
    public void shouldUseDefaultDelimitersWhenLineIsEmpty() {
        var result = delimiterFinder.findDelimitersOrUseDefault("//1,2,3,4");
        var expected = new String[] { ",", "\n" };
        assertArrayEquals(result, expected);
    }
}
