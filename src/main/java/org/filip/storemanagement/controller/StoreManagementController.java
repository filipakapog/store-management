package org.filip.storemanagement.controller;

import org.filip.storemanagement.controller.dto.CreateProductRequest;
import org.filip.storemanagement.controller.dto.CreateProductResponse;
import org.filip.storemanagement.controller.dto.GetProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/storemng/products")
public class StoreManagementController {

    @GetMapping("/{uuid}")
    public ResponseEntity<GetProductResponse> getProductById(@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok().body(new GetProductResponse(uuid, "Sunny Honey", "The sweet honey you need", 2, 12.5));
    }

    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        CreateProductResponse response = new CreateProductResponse(UUID.randomUUID().toString(),
                request.name(),
                request.description(),
                request.quantity(),
                request.price()
        );

        return ResponseEntity.ok().body(response);
    }

}
