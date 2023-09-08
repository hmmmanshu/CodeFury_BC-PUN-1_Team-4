package com.codefury.bugtracking.exceptions;

public class CouldNotCloseBugException extends Exception{
    public CouldNotCloseBugException() {
        super();
    }

    public CouldNotCloseBugException(String message) {
        super(message);
    }
}
