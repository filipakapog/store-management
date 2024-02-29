package org.filip.storemanagement.service.exception;

public class ProductNotFoundException extends ProductServiceException {
    public ProductNotFoundException() {
        super(404, "Product not found");
    }
}
