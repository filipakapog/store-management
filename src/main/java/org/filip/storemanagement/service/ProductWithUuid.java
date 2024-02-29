package org.filip.storemanagement.service;

import org.filip.storemanagement.service.exception.InvalidInputException;
import org.filip.storemanagement.service.exception.ProductServiceException;

import java.util.UUID;

public class ProductWithUuid {

    private UUID id;

    public ProductWithUuid(String uuidAsStr) {
        setId(uuidAsStr);
    }

    private void setId(String uuidAsStr) {
        try {
            this.id = UUID.fromString(uuidAsStr);
        } catch (Exception e) {
            throwInvalidUserInputException("The provided id is not a valid uuid value");
        }
    }

    public UUID getId() {
        return id;
    }

    private void throwInvalidUserInputException(String errorMessage) {
        throw new InvalidInputException(errorMessage);
    }
}
