package org.filip.storemanagement.controller.dto;

public record CreateProductResponse(String uuid, String name, String description, int quantity, double price) {
}
