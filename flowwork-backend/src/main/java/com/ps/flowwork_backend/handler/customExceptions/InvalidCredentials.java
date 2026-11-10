package com.ps.flowwork_backend.handler.customExceptions;

public class InvalidCredentials extends RuntimeException{
    public InvalidCredentials(String message){
        super(message);
    }
}
