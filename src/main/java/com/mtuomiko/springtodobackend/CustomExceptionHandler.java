package com.mtuomiko.springtodobackend;

import com.mtuomiko.springtodobackend.exception.CustomError;
import com.mtuomiko.springtodobackend.exception.NotAuthorizedException;
import com.mtuomiko.springtodobackend.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomError> notFoundHandler(HttpServletRequest request, NotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new CustomError("not found", ex.getMessage(), request.getRequestURI(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<CustomError> notAuthorizedHandler(HttpServletRequest request, NotAuthorizedException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new CustomError("forbidden", ex.getMessage(), request.getRequestURI(), HttpStatus.FORBIDDEN.value()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<CustomError> validationExceptionHandler(HttpServletRequest request, ValidationException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new CustomError("bad request", ex.getMessage(), request.getRequestURI(), HttpStatus.BAD_REQUEST.value()));
    }
}
