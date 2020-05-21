package com.application.org.Netflix.Application.ExceptionClasses;

import java.util.Date;

public class ErrorMessages {

    private Date date;
    private String message;

    public ErrorMessages(){

    }
    public ErrorMessages(Date date, String message) {
        this.date = date;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
