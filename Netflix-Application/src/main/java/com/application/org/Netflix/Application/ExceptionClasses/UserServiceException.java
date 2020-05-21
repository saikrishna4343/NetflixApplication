package com.application.org.Netflix.Application.ExceptionClasses;

public class UserServiceException extends RuntimeException{

    private static final long serialVersionUID = 891235631466810360L;

    public UserServiceException(String message){
        super(message);
    }
}
