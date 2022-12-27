package com.turing.exception;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(String message){
        super(message);
    }
}