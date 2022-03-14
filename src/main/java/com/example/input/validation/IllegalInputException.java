package com.example.input.validation;

public class IllegalInputException extends RuntimeException {

    public IllegalInputException(String input) {
        super("Provided input seems useless: " + input);
    }
}
