package com.djachtoma.exception;

public class PhysicianNotFoundException extends RuntimeException {

    public PhysicianNotFoundException(String message) {
        super(message);
    }
}
