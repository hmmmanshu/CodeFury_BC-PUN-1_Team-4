package com.codefury.bugtracking.exceptions;

/**
 * Exception to be thrown when a non employee is trying to register to the platform
 */
public class EmployeeDoesNotExistException extends Exception {
    public EmployeeDoesNotExistException(String message) {
        super(message);
    }
}
