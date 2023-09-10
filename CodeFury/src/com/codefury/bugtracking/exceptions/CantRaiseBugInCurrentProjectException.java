package com.codefury.bugtracking.exceptions;

public class CantRaiseBugInCurrentProjectException extends Exception{

    public CantRaiseBugInCurrentProjectException(String message) {
        super(message);
    }
}
