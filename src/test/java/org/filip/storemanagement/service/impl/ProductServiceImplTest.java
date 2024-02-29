package org.filip.storemanagement.service.impl;

import org.filip.storemanagement.repository.ProductRepository;
import org.filip.storemanagement.service.ProductService;
import org.filip.storemanagement.service.exception.ProductNotFoundException;
import org.filip.storemanagement.service.ProductWithUuid;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @Test
    void readProduct_productDoesNotExist_throwException() {
        // GIVEN
        String uuidAsStr = UUID.randomUUID().toString();
        ProductWithUuid productWithUuid = new ProductWithUuid(uuidAsStr);
        ProductService productService = new ProductServiceImpl(productRepository);

        // WHEN
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class,
                () -> productService.readProduct(productWithUuid));

        // THEN
        assertEquals("Product not found", exception.getMessage());
        assertEquals("Product not found", exception.getErrorMessage());
        assertEquals(404, exception.getHttpErrorCode());
    }
}
