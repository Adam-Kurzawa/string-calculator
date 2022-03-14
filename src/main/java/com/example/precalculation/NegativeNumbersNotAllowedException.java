package com.example.precalculation;

import java.util.List;

public class NegativeNumbersNotAllowedException extends RuntimeException {

    public NegativeNumbersNotAllowedException(List<Integer> negativeNumbers) {
        super("Negative numbers " + negativeNumbers + " were passed.");
    }
}
