package org.filip.storemanagement.controller;

import org.filip.storemanagement.controller.dto.CreateProductRequest;
import org.filip.storemanagement.controller.dto.CreateProductResponse;
import org.filip.storemanagement.controller.dto.GetProductResponse;
import org.filip.storemanagement.service.Product;
import org.filip.storemanagement.service.ProductService;
import org.filip.storemanagement.service.ProductWithUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/storemng/products")
public class StoreManagementController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        Product product = productService.createProduct(new Product(request));
        CreateProductResponse response = computCreateProductResponse(product);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<GetProductResponse> readProductById(@PathVariable("uuid") String uuid) {
        Product product = productService.readProduct(new ProductWithUuid(uuid));
        GetProductResponse response = computeGetProductResponse(product);
        return ResponseEntity.ok().body(response);
    }

    private CreateProductResponse computCreateProductResponse(Product product) {
        return new CreateProductResponse(
                product.getId().toString(),
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice()
        );
    }

    private GetProductResponse computeGetProductResponse(Product product) {
        return new GetProductResponse(
                product.getId().toString(),
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice()
        );
    }
}
