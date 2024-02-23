package org.filip.storemanagement.controller.dto;

public record CreateProductRequest(String name, String description, int quantity, double price) {
}
