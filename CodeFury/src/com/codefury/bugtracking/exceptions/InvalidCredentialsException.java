package com.codefury.bugtracking.exceptions;

/**
 * Exception to be thrown when invalid credentials haven been entered
 */
public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException() {
        super();
    }

    public InvalidCredentialsException(String message) {
        super(message);
    }
}
