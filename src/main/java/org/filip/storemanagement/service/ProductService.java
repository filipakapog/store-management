package org.filip.storemanagement.service;

public interface ProductService {

    Product createProduct(Product product);

    Product readProduct(ProductWithUuid product);
}
