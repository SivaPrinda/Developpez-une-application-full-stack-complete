package com.openclassrooms.mddapi.Exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Default constructor for the ValidationExceptionHandler.
 * Used by Spring to instantiate the exception advice component.
 */
@RestControllerAdvice
public class ValidationExceptionHandler {
  /**
   * Handles validation exceptions and returns detailed error messages.
   *
   * @param ex the exception containing validation errors
   * @return a ResponseEntity containing a map of field names and corresponding error messages
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage()));
    return ResponseEntity.badRequest().body(errors);
  }
}
