package com.example;

import java.util.regex.Pattern;

public class DelimiterFinder {
    private static final String DELIMITER_OF_DELIMITERS = "]\\[";
    private static final String CUSTOM_DELIMITER_PATTERN_START = "//";
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("//(\\[(\\D+)])");
    private static final String[] DEFAULT_DELIMITERS = { ",", "\n" };

    public String[] findDelimitersOrUseDefault(String input) {
        var matcher = CUSTOM_DELIMITER_PATTERN.matcher(input);
        var customDelimitersFound = matcher.find();
        var inputStartsWithPattern = input.startsWith(CUSTOM_DELIMITER_PATTERN_START);

        if(customDelimitersFound && inputStartsWithPattern) {
            var foundDelimitersSection = matcher.group();
            return foundDelimitersSection
                    .substring(CUSTOM_DELIMITER_PATTERN_START.length() + 1, foundDelimitersSection.length() - 1)
                    .split(DELIMITER_OF_DELIMITERS);
        } else
            return DEFAULT_DELIMITERS;
    }
}
