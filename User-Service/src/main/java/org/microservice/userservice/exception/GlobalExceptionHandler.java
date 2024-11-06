package org.microservice.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleRecordNotFoundException (RecordNotFoundException e) {
        ErrorDetails errorDetails = new ErrorDetails(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                new Date()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class
    })
    public ResponseEntity<ErrorDetails> requestExceptionHandler(Exception e) {
        ErrorDetails errorDetails = new ErrorDetails(
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                new Date()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorDetails> handleConflictException (ConflictException e) {
        ErrorDetails errorDetails = new ErrorDetails(
                e.getMessage(),
                HttpStatus.CONFLICT.value(),
                new Date()
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

}
