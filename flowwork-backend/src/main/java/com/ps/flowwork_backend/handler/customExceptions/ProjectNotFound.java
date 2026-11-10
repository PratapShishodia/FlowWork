package com.ps.flowwork_backend.handler.customExceptions;

public class ProjectNotFound extends RuntimeException{
    public ProjectNotFound(String message){
        super(message);
    }
}
