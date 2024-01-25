package com.collins.backend.exceptions;

public class NoteServiceException extends RuntimeException{

    private String message;

    public NoteServiceException(String message) {
        super(message);
    }

    public NoteServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
