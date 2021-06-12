package com.mtuomiko.springtodobackend.exception;

public class NotAuthorizedException extends RuntimeException{
    public NotAuthorizedException(String message) {
        super(message);
    }
}
