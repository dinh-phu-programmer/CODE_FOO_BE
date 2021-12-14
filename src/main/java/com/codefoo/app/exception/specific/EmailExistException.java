package com.codefoo.app.exception.specific;

public class EmailExistException extends Exception{
    public EmailExistException(String s) {
        super(s);
    }
}
