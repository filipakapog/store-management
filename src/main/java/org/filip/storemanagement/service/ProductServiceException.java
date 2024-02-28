package org.filip.storemanagement.service;

public class ProductServiceException extends RuntimeException {
    private final int httpErrorCode;
    private final String errorMessage;

    public ProductServiceException(int httpErrorCode, String errorMessage) {
        super(errorMessage);
        this.httpErrorCode = httpErrorCode;
        this.errorMessage = errorMessage;
    }

    public static ProductServiceException productDoesNotExist() {
        return new ProductServiceException(404, "Product does not exist");
    }

    public int getHttpErrorCode() {
        return httpErrorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
