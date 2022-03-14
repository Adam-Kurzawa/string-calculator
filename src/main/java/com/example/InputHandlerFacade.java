package com.example;

import com.example.input.delimiter.DelimiterFinder;
import com.example.input.parser.StringToIntParser;
import com.example.input.tokenizer.InputTokenizer;
import com.example.input.validation.InputValidator;

import java.util.List;

public class InputHandlerFacade {
    private final DelimiterFinder delimiterFinder;
    private final InputValidator inputValidator;
    private final InputTokenizer inputTokenizer;
    private final StringToIntParser parser;

    public InputHandlerFacade(DelimiterFinder delimiterFinder, InputValidator inputValidator, InputTokenizer inputTokenizer, StringToIntParser parser) {
        this.delimiterFinder = delimiterFinder;
        this.inputValidator = inputValidator;
        this.inputTokenizer = inputTokenizer;
        this.parser = parser;
    }

    public List<Integer> getNumbers(String input) {
        var delimiter = delimiterFinder.findDelimitersOrUseDefault(input);
        inputValidator.validate(delimiter, input);
        var tokens = inputTokenizer.tokenize(delimiter, input);
        return parser.parse(tokens);
    }
}
