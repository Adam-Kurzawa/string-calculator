package com.example.input.validation;

import com.example.input.delimiter.Delimiters;

import java.util.regex.Pattern;

/**
 * InputValidator sprawdza czy przekazany String jest pełny, tzn:
 * - nie kończy się "\n", "," albo innym znakiem odstępu
 * - nie jest śmieciowym ciągiem znaków
 */
public class InputValidator {
    private static final String SLASHES = "//";
    private static final String IMPROPER_START_REASON = "Improper input start";
    private static final String IMPROPER_END_REASON = "Improper input end";
    private static final String MALFORMED_REASON = "Malformed input";

    public void validate(Delimiters delimiters, String input) {
        if(input.isBlank())
            return;

        throwIfInputStartsImproperly(input);
        throwIfInputEndsImproperly(input);
        throwIfInputIsMalformed(delimiters, input);
    }

    private void throwIfInputStartsImproperly(String input) {
        if(!(input.startsWith(SLASHES) || startsWithNumber(input)))
            throw new IllegalInputException(input, IMPROPER_START_REASON);
    }

    private void throwIfInputEndsImproperly(String input) {
        if(!endsWithNumber(input))
            throw new IllegalInputException(input, IMPROPER_END_REASON);
    }

    private void throwIfInputIsMalformed(Delimiters delimiters, String input) {
        var delimiterMatcher = delimiters.toPattern().matcher(input);
        var hasMatchOnDelimiting = delimiterMatcher.find();

        if(!hasMatchOnDelimiting) {
            var numericMatcher = Pattern.compile("\\d*").matcher(input);
            var hasNumbers = numericMatcher.matches();

            if(!hasNumbers)
                throw new IllegalInputException(input, MALFORMED_REASON);
        }
    }

    private boolean startsWithNumber(String input) {
        return Character.isDigit(input.charAt(0));
    }

    private boolean endsWithNumber(String input) {
        var lastIndex = input.length() - 1;
        return Character.isDigit(input.charAt(lastIndex));
    }
}
