package com.example;

import com.example.input.delimiter.DelimiterFinder;
import com.example.input.parser.StringToIntParser;
import com.example.input.tokenizer.InputTokenizer;
import com.example.input.validation.IllegalInputException;
import com.example.input.validation.InputValidator;
import com.example.precalculation.IntegerFilter;
import com.example.precalculation.IntegerValidator;
import com.example.precalculation.NegativeNumbersNotAllowedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalculatorTest {

    private final StringCalculator stringCalculator = new StringCalculator(
            new InputHandlerFacade(new DelimiterFinder(), new InputValidator(), new InputTokenizer(), new StringToIntParser()),
            new IntegerValidator(),
            new IntegerFilter()
    );

    @Test
    public void shouldReturnZeroForEmptyString() {
        var result = stringCalculator.add("");
        assertEquals(0, result);
    }

    @Test
    public void shouldReturnOneForConstantString() {
        var result = stringCalculator.add("1");
        assertEquals(1, result);
    }

    @Test
    public void shouldReturnOneForConstantStringWithCustomDelimiters() {
        var result = stringCalculator.add("//[=]\n5");
        assertEquals(5, result);
    }

    @Test
    public void shouldSumNumbersFromDelimitedString() {
        var result = stringCalculator.add("1,2,3");
        assertEquals(6, result);
    }

    @Test
    public void shouldThrowForMalformedInput() {
        var exception = assertThrows(
                IllegalInputException.class,
                () -> stringCalculator.add("not-a-number")
        );

        assertEquals(exception.getMessage(), "Provided input seems useless. Reason: Improper input start Input: not-a-number");
    }

    @Test
    public void shouldSumNumbersIgnoringNonNumbers() {
        var result = stringCalculator.add("1,a,2,3,4");
        assertEquals(10, result);
    }

    @Test
    public void shouldSumNumbersWhenMultipleCustomDelimiters() {
        var result = stringCalculator.add("//[;][-]1;a-2-3;4");
        assertEquals(10, result);
    }

    @Test
    public void shouldSumNumbersWhenNewLines() {
        var result = stringCalculator.add("1\n2\na\n3,4");
        assertEquals(10, result);
    }

    @Test
    public void shouldSumNumbersWhenNewLinesAndCustomDelimiters() {
        var result = stringCalculator.add("//[-]\n1\n2\na\n3-4");
        assertEquals(10, result);
    }

    @Test
    public void shouldThrowOnNegatives() {
        var exception = assertThrows(
                NegativeNumbersNotAllowedException.class,
                () -> stringCalculator.add("//[;]\n1\n2\na\n3;-4\n-5")
        );

        assertEquals(exception.getMessage(), "Negative numbers [-4, -5] were passed.");
    }

    @Test
    public void shouldOmitValuesGreaterThanThousand() {
        var result = stringCalculator.add("//[;][=]\n1;2;\na\n3=4;2500");
        assertEquals(10, result);
    }

    @Test
    public void shouldSupportMulitcharDelimiters() {
        var result = stringCalculator.add("//[;;;][===]\n1;;;2;;;\n3\n4===5");
        assertEquals(15, result);
    }
}
