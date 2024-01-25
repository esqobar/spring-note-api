package com.collins.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoteNotFoundException extends RuntimeException{
    private String message;

    public NoteNotFoundException(String message) {
        super(message);
    }
}
