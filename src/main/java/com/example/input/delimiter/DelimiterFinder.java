package com.example.input.delimiter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Analizuje nagłówek i zwraca znaki odstępu.
 */
public class DelimiterFinder {
    private static final String NEW_LINE = "\n";
    private static final String DELIMITER_OF_DELIMITERS = "]\\[";
    private static final String CUSTOM_DELIMITER_PATTERN_START = "//";
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//(\\[(\\D+)])");
    private static final Delimiters DEFAULT_DELIMITERS = new Delimiters(List.of(",", NEW_LINE), 0);

    public Delimiters findDelimitersOrUseDefault(String input) {
        var matcher = CUSTOM_DELIMITER_PATTERN.matcher(input);
        var customDelimitersFound = matcher.find();
        var inputStartsWithPattern = input.startsWith(CUSTOM_DELIMITER_PATTERN_START);

        if(customDelimitersFound && inputStartsWithPattern) {
            var foundDelimitersSection = matcher.group();
            var delimitersHeaderLength = foundDelimitersSection.length();
            var delimiters = extractDelimiters(foundDelimitersSection, delimitersHeaderLength);
            return new Delimiters(delimiters, delimitersHeaderLength);
        } else
            return DEFAULT_DELIMITERS;
    }

    private List<String> extractDelimiters(String foundDelimitersSection, int delimitersHeaderLength) {
        var delimiters = Arrays.stream(
                foundDelimitersSection
                        .substring(CUSTOM_DELIMITER_PATTERN_START.length() + 1, delimitersHeaderLength - 1)
                        .split(DELIMITER_OF_DELIMITERS)
        ).toList();

        var mutableDelimiters = new LinkedList<>(delimiters);
        mutableDelimiters.add(NEW_LINE);
        return mutableDelimiters;
    }
}
