package org.filip.storemanagement.service;

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
        throw new ProductServiceException(400, errorMessage);
    }
}
