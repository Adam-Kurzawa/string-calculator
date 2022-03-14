package com.example.precalculation;

import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class IntegerFilter {

    private final List<Predicate<Integer>> predicates = List.of(
            x -> x <= 1000
    );

    public List<Integer> filter(List<Integer> values) {
        var result = values;

        for (var predicate : predicates)
            result = result.stream().filter(predicate).collect(toList());

        return result;
    }
}
