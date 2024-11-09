package org.microservice.likeservice.exception;

public class UnauthenticatedException extends RuntimeException {
    public UnauthenticatedException (String message) {
        super("Unauthenticated");
    }
}
