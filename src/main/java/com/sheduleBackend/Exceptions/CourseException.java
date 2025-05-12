package com.sheduleBackend.Exceptions;

public class CourseException extends RuntimeException {

    private String message;
    public CourseException() {

    }
    public CourseException(String msg) {
        super(msg);
        this.message = msg;
    }


}
