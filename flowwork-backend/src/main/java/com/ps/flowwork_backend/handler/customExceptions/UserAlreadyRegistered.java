package com.ps.flowwork_backend.handler.customExceptions;

public class UserAlreadyRegistered extends RuntimeException{
    public UserAlreadyRegistered(String message){
        super(message);
    }
}
