package com.example.input.tokenizer;

import com.example.input.delimiter.Delimiters;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InputTokenizerTest {
    private final InputTokenizer inputTokenizer = new InputTokenizer();

    @Test
    public void shouldReturnEmptyListOnEmptyString() {
        var delimiters = new Delimiters(List.of(","), 0);
        var result = inputTokenizer.tokenize(delimiters, "");
        assertEquals(List.of(), result);
    }

    @Test
    public void shouldReturnEmptyListOnBlankString() {
        var delimiters = new Delimiters(List.of(","), 0);
        var result = inputTokenizer.tokenize(delimiters, "     ");
        assertEquals(List.of(), result);
    }

    @Test
    public void shouldReturnSingleElementListOnSingleElementString() {
        var delimiters = new Delimiters(List.of(","), 0);
        var result = inputTokenizer.tokenize(delimiters, "12");
        assertEquals(List.of("12"), result);
    }

    @Test
    public void shouldReturnListOnMultipleValuesString() {
        var delimiters = new Delimiters(List.of(","), 0);
        var result = inputTokenizer.tokenize(delimiters, "1,2,3,4");
        assertEquals(List.of("1", "2", "3", "4"), result);
    }

    @Test
    public void shouldReturnListOnMultipleValuesStringAndManyDelimiters() {
        var delimiters = new Delimiters(List.of(",", "-", " "), 0);
        var result = inputTokenizer.tokenize(delimiters, "1,2,3 4-5");
        assertEquals(List.of("1", "2", "3", "4", "5"), result);
    }

    @Test
    public void shouldReturnListOnMultipleValuesStringWithCustomHeaderAndManyDelimiters() {
        var delimiters = new Delimiters(List.of(",", "-", " "), 11);
        var result = inputTokenizer.tokenize(delimiters, "//[,][-][ ]1,2,3 4-5");
        assertEquals(List.of("1", "2", "3", "4", "5"), result);
    }
}
