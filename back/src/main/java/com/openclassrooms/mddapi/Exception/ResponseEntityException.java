package com.openclassrooms.mddapi.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Custom exception used to return a specific HTTP status and message.
 *
 * <p>This exception is typically thrown in service or controller layers
 * to return controlled error responses with custom messages and associated HTTP status codes.</p>
 *
 * @see org.springframework.http.HttpStatus
 */
@Getter
public class ResponseEntityException extends RuntimeException {

    /**
     * HTTP status code to be returned in the response.
     */
    private final HttpStatus status;
    /**
     * Custom message to be included in the response.
     */
    private final String message;

    /**
     * Constructs a new ResponseEntityException with a formatted message and HTTP status.
     *
     * @param status the HTTP status to be returned
     * @param message the message format string
     * @param args arguments referenced by the format specifiers in the message
     */
    public ResponseEntityException(HttpStatus status, String message, Object... args) {
        super(String.format(message, args));
        this.status = status;
        this.message = String.format(message, args);
    }
}