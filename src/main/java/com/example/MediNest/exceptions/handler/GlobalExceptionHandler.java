package com.example.MediNest.exceptions.handler;


import com.example.MediNest.exceptions.AuthorizationException;
import com.example.MediNest.exceptions.DataNotFoundException;
import com.example.MediNest.exceptions.DataValidationException;
import com.example.MediNest.model.error.ErrorResponse;
import com.example.MediNest.model.error.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.example.MediNest")
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerDataNotFoundException(DataNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ErrorType.MISSING_DATA);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataValidationException.class)
    public ResponseEntity<ErrorResponse> handlerDataValidationException(DataValidationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ErrorType.INVALID_DATA);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<ErrorResponse> handlerAuthorizationException(AuthorizationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ErrorType.UNAUTHORIZED);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerGlobalException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("An Unexpected Error Occurred", ErrorType.INTERNAL_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
