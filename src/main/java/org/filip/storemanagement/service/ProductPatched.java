package org.filip.storemanagement.service;

import org.filip.storemanagement.controller.dto.PatchProductRequest;
import org.filip.storemanagement.service.exception.ProductServiceException;

import java.util.Objects;
import java.util.UUID;

public class ProductPatched {
    private UUID id;
    private String name;
    private String description;
    private Integer quantity;
    private Double price;

    public ProductPatched(PatchProductRequest request, String id) {
        throwExceptionIfNull(request);
        setName(request.name());
        setDescription(request.description());
        setQuantity(request.quantity());
        setPrice(request.price());
        setId(id);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    private void throwExceptionIfNull(Object request) {
        if (null == request) {
            throwInvalidUserInputException("The request does not contain any fields");
        }
    }

    private void setName(String name) {
        if (isEmptyString(name)) {
            throwInvalidUserInputException("Field 'name' cannot be an empty string");
        }
        this.name = name;
    }

    private void setDescription(String description) {
        if (isEmptyString(description)) {
            throwInvalidUserInputException("Field 'description' cannot be an empty string");
        }
        this.description = description;
    }

    private void setQuantity(Integer quantity) {
        if (quantity != null && quantity < 0) {
            throwInvalidUserInputException("Field 'quantity' cannot be a negative value");
        }
        this.quantity = quantity;
    }

    private void setPrice(Double price) {
        if (price != null && price < 0) {
            throwInvalidUserInputException("Field 'price' cannot be a negative value");
        }
        this.price = price;
    }

    private boolean isEmptyString(String str) {
        return str != null && str.trim().length() == 0;
    }

    private void throwInvalidUserInputException(String errorMessage) {
        throw new ProductServiceException(400, errorMessage);
    }

    private void setId(String uuidAsStr) {
        try {
            this.id = UUID.fromString(uuidAsStr);
        } catch (Exception e) {
            throwInvalidUserInputException("The provided id is not a valid uuid value");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPatched product = (ProductPatched) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
