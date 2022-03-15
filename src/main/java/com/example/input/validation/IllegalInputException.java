package com.example.input.validation;

public class IllegalInputException extends RuntimeException {

    public IllegalInputException(String input, String reason) {
        super("Provided input seems useless. Reason: " + reason + " Input: " + input);
    }
}
