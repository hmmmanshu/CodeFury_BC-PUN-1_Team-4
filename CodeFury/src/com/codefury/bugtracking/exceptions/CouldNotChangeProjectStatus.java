package com.codefury.bugtracking.exceptions;

public class CouldNotChangeProjectStatus extends Exception{
    public CouldNotChangeProjectStatus() {
        super();
    }

    public CouldNotChangeProjectStatus(String message) {
        super(message);
    }
}
