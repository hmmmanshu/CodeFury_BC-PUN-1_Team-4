package com.codefury.bugtracking.exceptions;

public class CouldNotGetProjectsListException extends Exception{
    public CouldNotGetProjectsListException() {
        super();
    }

    public CouldNotGetProjectsListException(String message) {
        super(message);
    }
}
