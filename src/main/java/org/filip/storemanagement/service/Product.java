package org.filip.storemanagement.service;

import org.filip.storemanagement.controller.dto.CreateProductRequest;
import org.filip.storemanagement.repository.ProductE;
import org.filip.storemanagement.service.exception.ProductServiceException;

import java.util.Objects;
import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private String description;
    private Integer quantity;
    private Double price;

    public Product(CreateProductRequest createProductRequest) {
        throwExceptionIfNull(createProductRequest);
        setName(createProductRequest.name());
        setDescription(createProductRequest.description());
        setQuantity(createProductRequest.quantity());
        setPrice(createProductRequest.price());
        generateId();
    }

    public Product(ProductE entity) {
        setId(UUID.fromString(entity.getId()));
        setName(entity.getName());
        setDescription(entity.getDescription());
        setQuantity(entity.getQuantity());
        setPrice(entity.getPrice());
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

    private void generateId() {
        this.id = UUID.randomUUID();
    }

    private void throwExceptionIfNull(CreateProductRequest createProductRequest) {
        if (null == createProductRequest) {
            throwInvalidUserInputException("The request does not contain any fields");
        }
    }

    private void setId(UUID id) {
        this.id = id;
    }

    private void setName(String name) {
        if (isEmptyString(name)) {
            throwInvalidUserInputException("Field 'name' cannot be empty or null");
        }
        this.name = name;
    }

    private void setDescription(String description) {
        if (isEmptyString(description)) {
            throwInvalidUserInputException("Field 'description' cannot be empty or null");
        }
        this.description = description;
    }

    private void setQuantity(int quantity) {
        if (quantity < 0) {
            throwInvalidUserInputException("Field 'quantity' cannot be a negative value");
        }
        this.quantity = quantity;
    }

    private void setPrice(double price) {
        if (price < 0) {
            throwInvalidUserInputException("Field 'price' cannot be a negative value");
        }
        this.price = price;
    }

    private boolean isEmptyString(String str) {
        return str == null || str.trim().length() == 0;
    }

    private void throwInvalidUserInputException(String errorMessage) {
        throw new ProductServiceException(400, errorMessage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
