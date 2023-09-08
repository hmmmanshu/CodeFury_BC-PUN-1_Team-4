package com.codefury.bugtracking.exceptions;

public class CouldNotAddProjectException extends Exception {
    public CouldNotAddProjectException() {
        super();
    }

    public CouldNotAddProjectException(String message) {
        super(message);
    }
}
