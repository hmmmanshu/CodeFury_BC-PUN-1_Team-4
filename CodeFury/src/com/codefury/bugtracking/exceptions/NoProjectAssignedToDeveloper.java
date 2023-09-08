package com.codefury.bugtracking.exceptions;

public class NoProjectAssignedToDeveloper extends Exception{
    public NoProjectAssignedToDeveloper() {
        super();
    }

    public NoProjectAssignedToDeveloper(String message) {
        super(message);
    }
}
