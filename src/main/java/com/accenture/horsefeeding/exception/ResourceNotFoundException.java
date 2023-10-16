package com.accenture.horsefeeding.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceType, String fieldName, Object fieldValue) {
        super(resourceType + " with " + fieldName + " [" + fieldValue + "] not found.");
    }
}

