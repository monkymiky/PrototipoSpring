package com.unipd.synclab.project1.exception;

public class ReferencedResourceNotFoundException extends RuntimeException {
    public ReferencedResourceNotFoundException(String message) {
        super(message);
    }
}