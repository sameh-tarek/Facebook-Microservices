package org.microservice.likeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<ErrorDetails> handleRecordNotFoundException (RecordNotFoundException e) {
        ErrorDetails errorDetails = new ErrorDetails(
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                new Date()
        );
        return ResponseEntity.
                status(HttpStatus.NOT_FOUND).
                body(errorDetails);
    }

    public ResponseEntity<ErrorDetails> handleUnauthenticatedException (UnauthenticatedException e) {
        ErrorDetails errorDetails = new ErrorDetails(
                e.getMessage(),
                HttpStatus.NOT_ACCEPTABLE.value(),
                new Date()
        );
        return ResponseEntity.
                status(HttpStatus.NOT_ACCEPTABLE).
                body(errorDetails);
    }
}
