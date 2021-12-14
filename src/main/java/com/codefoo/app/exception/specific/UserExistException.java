package com.codefoo.app.exception.specific;

public class UserExistException extends Exception{
    public UserExistException(String s) {
        super(s);
    }
}
