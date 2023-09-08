package com.codefury.bugtracking.exceptions;

public class EmployeeAlreadyRegisteredException extends Exception {
    public EmployeeAlreadyRegisteredException() {
        super();
    }

    public EmployeeAlreadyRegisteredException(String message) {
        super(message);
    }
}
