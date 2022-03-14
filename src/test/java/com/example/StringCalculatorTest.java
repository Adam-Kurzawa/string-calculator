package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {
    private final StringCalculator stringCalculator = new StringCalculator();

    @Test
    public void emptyStringShouldReturnZero() {
        var result = stringCalculator.add("");
        assertEquals(result, 0);
    }

    @Test
    public void onlyNumberInStringShouldReturnThatNumber() {
        var result = stringCalculator.add("1");
        assertEquals(result, 1);
    }

    @Test
    public void manyNumbersInStringShouldReturnSumOfTheseNumbers() {
        var result = stringCalculator.add("1,2,3");
        assertEquals(result, 6);
    }

    @Test
    public void gibberishInputShouldReturnZero() {
        var result = stringCalculator.add("not-a-number");
        assertEquals(result, 0);
    }

    @Test
    public void anyNonNumbersInProperInputShouldBeIgnored() {
        var result = stringCalculator.add("1,a,2,3,4");
        assertEquals(result, 10);
    }
}
