package com.codefury.bugtracking.exceptions;

public class CantRaiseBugInCurrentProjectException extends Exception{
    public CantRaiseBugInCurrentProjectException() {
        super();
    }

    public CantRaiseBugInCurrentProjectException(String message) {
        super(message);
    }
}
