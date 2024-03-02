package org.filip.storemanagement.controller.dto;

public record PatchProductRequest(String uuid, String name, String description, Integer quantity, Double price) {
}
