package org.filip.storemanagement.service.exception;

public class InvalidInputException extends ProductServiceException {
    public InvalidInputException(String message) {
        super(400, message);
    }
}
