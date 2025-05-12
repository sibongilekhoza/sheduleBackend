package com.sheduleBackend.Exceptions;

public class UserException extends RuntimeException{
    private String message;
    public  UserException(String msg){
        super(msg);
        this.message =msg;
    }

   
}
