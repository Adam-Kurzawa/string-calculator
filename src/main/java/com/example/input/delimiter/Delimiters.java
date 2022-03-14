package com.example.input.delimiter;

import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

public record Delimiters(List<String> delimiters, int headerLength) {
    private static final String REGEXP_GROUP_OR = "|";

    public Pattern toPattern() {
        return Pattern.compile(toRegExp());
    }

    public String toRegExp() {
        return delimiters
                .stream()
                .map(Delimiters::toRegExpGroup)
                .collect(joining(REGEXP_GROUP_OR));
    }

    private static String toRegExpGroup(String delimiter) {
        return "(\\" + delimiter + ")";
    }
}
