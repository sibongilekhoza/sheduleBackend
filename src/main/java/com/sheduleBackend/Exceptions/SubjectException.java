package com.sheduleBackend.Exceptions;

public class SubjectException extends RuntimeException {
    private String message;
    public SubjectException(String msg) {
        super(msg);
        this.message = msg;
    }

    public SubjectException() {
    }
}
