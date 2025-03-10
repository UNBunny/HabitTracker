package com.springlearn.backend.exception;

public class ForbiddenException extends RuntimeException {
    ForbiddenException(String message) {
        super(message);
    }
}
