package org.filip.storemanagement.controller.dto;

public record GetProductResponse(String uuid, String name, String description, int quantity, double price) {

}
