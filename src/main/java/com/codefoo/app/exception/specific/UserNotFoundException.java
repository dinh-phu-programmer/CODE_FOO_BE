package com.codefoo.app.exception.specific;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String s) {
        super(s);
    }
}
