package com.example.orderwise.exception;

public class InvalidInputException extends RuntimeException{
    private static final long serialVersionUID = 1790326657186815893L;

    public InvalidInputException() {
    }

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputException(Throwable cause) {
        super(cause);
    }
}
