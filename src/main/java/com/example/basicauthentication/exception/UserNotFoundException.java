package com.example.basicauthentication.exception;

public class UserNotFoundException extends RuntimeException{

    private static final String MESSAGE="USER NOT FOUND";

    public UserNotFoundException(){
        super(MESSAGE);
    }
}
