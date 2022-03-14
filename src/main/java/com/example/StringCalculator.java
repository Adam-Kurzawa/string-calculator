package com.example;

import com.example.precalculation.IntegerFilter;
import com.example.precalculation.IntegerValidator;

import java.util.List;

public class StringCalculator {
    private final InputHandlerFacade inputHandlerFacade;
    private final IntegerValidator integerValidator;
    private final IntegerFilter integerFilter;

    public StringCalculator(InputHandlerFacade inputHandlerFacade, IntegerValidator integerValidator, IntegerFilter integerFilter) {
        this.inputHandlerFacade = inputHandlerFacade;
        this.integerValidator = integerValidator;
        this.integerFilter = integerFilter;
    }

    public int add(String input) {
        var numbers = inputHandlerFacade.getNumbers(input);
        integerValidator.validate(numbers);
        var filteredNumbers = integerFilter.filter(numbers);
        return sum(filteredNumbers);
    }

    private int sum(List<Integer> numbers) {
        return numbers
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
