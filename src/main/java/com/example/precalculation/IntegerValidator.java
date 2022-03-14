package com.example.precalculation;

import java.util.List;

public class IntegerValidator {

    public void validate(List<Integer> values) {
        throwIfContainsNegativeNumbers(values);
    }

    private void throwIfContainsNegativeNumbers(List<Integer> values) {
        var negatives = values.stream().filter(x -> x < 0).toList();

        if(!negatives.isEmpty())
            throw new NegativeNumbersNotAllowedException(negatives);
    }
}
