package com.ps.flowwork_backend.handler.customExceptions;

public class OTPNotVerified extends RuntimeException{
    public OTPNotVerified(String message){
        super(message);
    }
}
