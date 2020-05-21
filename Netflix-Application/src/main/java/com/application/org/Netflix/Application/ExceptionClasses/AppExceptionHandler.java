package com.application.org.Netflix.Application.ExceptionClasses;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserServiceException.class})
    public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request){

        String errorMessageDescription = ex.getLocalizedMessage();

        if(errorMessageDescription == null)errorMessageDescription =ex.toString();
        ErrorMessages errorMessages =new ErrorMessages(new Date(),errorMessageDescription );
        return  new ResponseEntity<Object>(errorMessages, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> handleNullPointException(NullPointerException ex, WebRequest request){

        String errorMessageDescription = ex.getLocalizedMessage();

        if(errorMessageDescription == null)errorMessageDescription =ex.toString();
        ErrorMessages errorMessages =new ErrorMessages(new Date(),errorMessageDescription );
        return  new ResponseEntity<Object>(errorMessages, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){

        String errorMessageDescription = ex.getLocalizedMessage();

        if(errorMessageDescription == null)errorMessageDescription =ex.toString();
        ErrorMessages errorMessages =new ErrorMessages(new Date(),errorMessageDescription );
        return  new ResponseEntity<Object>(errorMessages, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
