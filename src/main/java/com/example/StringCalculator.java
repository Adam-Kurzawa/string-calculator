package com.example;

import java.util.Arrays;
import java.util.stream.Stream;

// StringCalculator pipeline:
// 1) InputValidator (sprawdza czy input jest pełny (nie kończy się na /n albo ,),czy nie jest śmieciowy) STRING -> STRING
// 2) InputTokenizer (znajduje delimiter a jak nie to używa domyślnego) STRING -> ARRAY[STRING]
// 3) StringToIntParser (parsuje string na int, odrzuca nie-liczby) ARRAY[STRING] -> ARRAY[INT]
// 4) IntegerValidator (lista reguł, sprawdza czy nie negatywna, rzuca wyjątki) ARRAY[INT] -> ARRAY[INT]
// 5) Filter (przyjmuje listę reguł, odrzuca nie przechodzące) ARRAY[INT] -> ARRAY[INT]
// 6) Calculator (zlicz wszystko)

public class StringCalculator {
    private static final String NEW_LINE = "\n";
    private static final String DELIMITER = ",";

    public int add(String input) {
        return tokenize(input)
                .mapToInt(this::tryParseInt)
                .sum();
    }

    private int tryParseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    private Stream<String> tokenize(String input) {
        return Arrays.stream(
                replaceNewLinesWithDelimiter(input).split(DELIMITER)
        );
    }

    private String replaceNewLinesWithDelimiter(String input) {
        return input.replace(NEW_LINE, DELIMITER);
    }
}
