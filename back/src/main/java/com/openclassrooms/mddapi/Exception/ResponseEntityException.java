package com.openclassrooms.mddapi.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResponseEntityException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    public ResponseEntityException(HttpStatus status, String message, Object... args) {
        super(String.format(message, args));
        this.status = status;
        this.message = String.format(message, args);
    }
}