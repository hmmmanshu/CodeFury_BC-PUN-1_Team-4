package com.codefury.bugtracking.exceptions;

public class InvalidBugMarkedForClosingException extends Exception {
    public InvalidBugMarkedForClosingException() {
        super();
    }

    public InvalidBugMarkedForClosingException(String message) {
        super(message);
    }
}
