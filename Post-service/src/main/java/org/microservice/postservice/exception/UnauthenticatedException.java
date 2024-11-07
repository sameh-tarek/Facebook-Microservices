package org.microservice.postservice.exception;

public class UnauthenticatedException extends RuntimeException {
    public UnauthenticatedException (String message) {
        super("Unauthenticated");
    }
}
