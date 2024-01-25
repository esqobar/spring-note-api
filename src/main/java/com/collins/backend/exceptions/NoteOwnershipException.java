package com.collins.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoteOwnershipException extends RuntimeException{
    private String message;

    public NoteOwnershipException(String message) {
        super(message);
    }
}
