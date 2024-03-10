package org.filip.storemanagement.controller;

import org.filip.storemanagement.controller.dto.*;
import org.filip.storemanagement.service.Product;
import org.filip.storemanagement.service.ProductPatched;
import org.filip.storemanagement.service.ProductService;
import org.filip.storemanagement.service.ProductWithUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/storemng/products")
public class StoreManagementController {

    private static final Logger LOGGER = Logger.getLogger(StoreManagementController.class.getName());

    @Autowired
    private ProductService productService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        log("Received create Product request with body: " + request);
        Product product = productService.createProduct(new Product(request));
        CreateProductResponse response = computCreateProductResponse(product);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<GetProductResponse> readProductById(@PathVariable("uuid") String uuid) {
        log("Received read Product request for Product with id: " + uuid);
        Product product = productService.readProduct(new ProductWithUuid(uuid));
        GetProductResponse response = computeGetProductResponse(product);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping("/{uuid}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<UpdateProductResponse> patchProductById(@PathVariable("uuid") String uuid,
                                                                  @RequestBody PatchProductRequest request) {
        log("Received patch Product request for Product with id: " + uuid + " with body: " + request);
        Product product = productService.patchProduct(new ProductPatched(request, uuid));
        UpdateProductResponse response = computeUpdateProductResponse(product);
        return ResponseEntity.ok().body(response);
    }

    private void log(String message) {
        LOGGER.log(Level.INFO, () -> message);
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
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice()
        );
    }

    private UpdateProductResponse computeUpdateProductResponse(Product product) {
        return new UpdateProductResponse(
                product.getName(),
                product.getDescription(),
                product.getQuantity(),
                product.getPrice()
        );
    }
}
