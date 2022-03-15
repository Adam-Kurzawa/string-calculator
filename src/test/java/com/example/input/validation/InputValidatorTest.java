package com.example.input.validation;

import com.example.input.delimiter.Delimiters;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InputValidatorTest {
    private final InputValidator inputValidator = new InputValidator();

    @Test
    public void shouldThrowIfInputStartsWithLetter() {
        var delimiters = new Delimiters(List.of(","), 0);

        var exception = assertThrows(
                IllegalInputException.class,
                () -> inputValidator.validate(delimiters, "A//[,]1,2,3")
        );

        assertEquals(exception.getMessage(), "Provided input seems useless. Reason: Improper input start Input: A//[,]1,2,3");
    }

    @Test
    public void shouldThrowIfInputNotSeemsProperlyDelimited() {
        var delimiters = new Delimiters(List.of(","), 0);

        var exception = assertThrows(
                IllegalInputException.class,
                () -> inputValidator.validate(delimiters, "1-2-3")
        );

        assertEquals(exception.getMessage(), "Provided input seems useless. Reason: Malformed input Input: 1-2-3");
    }

    @Test
    public void shouldNotThrowIfInputSeemsProperlyDelimited() {
        var delimiters = new Delimiters(List.of(","), 0);
        assertDoesNotThrow(() -> inputValidator.validate(delimiters, "1,2,3"));
    }

    @Test
    public void shouldNotThrowIfInputIsJustOneNumber() {
        var delimiters = new Delimiters(List.of(","), 0);
        assertDoesNotThrow(() -> inputValidator.validate(delimiters, "13"));
    }

    @Test
    public void shouldThrowIfInputEndsWithDelimiter() {
        var delimiters = new Delimiters(List.of(","), 0);

        var exception = assertThrows(
                IllegalInputException.class,
                () -> inputValidator.validate(delimiters, "//[,]1,2,3,")
        );

        assertEquals(exception.getMessage(), "Provided input seems useless. Reason: Improper input end Input: //[,]1,2,3,");
    }
}
