package com.codefury.bugtracking.exceptions;

public class CouldNotGetBugsList extends Exception{
    public CouldNotGetBugsList() {
        super();
    }

    public CouldNotGetBugsList(String message) {
        super(message);
    }
}
