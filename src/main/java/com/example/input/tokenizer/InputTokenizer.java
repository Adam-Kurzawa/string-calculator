package com.example.input.tokenizer;

import com.example.input.delimiter.Delimiters;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Zamienia ciąg znaków na listę liczb (jako string).
 * Ucina header ze znakami odstępów.
 */
public class InputTokenizer {

    public List<String> tokenize(Delimiters delimiters, String input) {
        if(input.isBlank())
            return List.of();
        else {
            var trimmedInput = input.substring(delimiters.headerLength());
            var regExp = delimiters.toRegExp();
            return Arrays.stream(trimmedInput.split(regExp)).collect(toList());
        }
    }
}
