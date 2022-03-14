package com.example.input.parser;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Parsuje wyraz do liczby.
 * Pomija nie-liczby.
 */
public class StringToIntParser {

    public List<Integer> parse(List<String> values) {
        return values
                .stream()
                .map(StringToIntParser::tryParse)
                .collect(toList());
    }

    private static int tryParse(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }
}
