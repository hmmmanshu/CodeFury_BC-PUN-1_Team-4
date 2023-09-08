package com.codefury.bugtracking.exceptions;

public class CouldNotAddEmployeeException extends Exception {
    public CouldNotAddEmployeeException() {
        super();
    }

    public CouldNotAddEmployeeException(String message) {
        super(message);
    }
}
