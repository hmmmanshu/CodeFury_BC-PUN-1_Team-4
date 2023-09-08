package com.codefury.bugtracking.exceptions;

public class PasswordsDoNotMatchException extends Exception{
    public PasswordsDoNotMatchException(String message){
        super(message);
    }
    public PasswordsDoNotMatchException(){
        super();
    }
}
