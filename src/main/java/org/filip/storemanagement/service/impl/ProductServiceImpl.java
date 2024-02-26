package org.filip.storemanagement.service.impl;

import org.filip.storemanagement.repository.ProductE;
import org.filip.storemanagement.repository.ProductRepository;
import org.filip.storemanagement.service.Product;
import org.filip.storemanagement.service.ProductService;
import org.filip.storemanagement.service.ProductWithUuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.filip.storemanagement.service.ProductServiceException.productDoesNotExist;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        ProductE productE = new ProductE(product);
        productRepository.save(productE);
        return product;
    }

    @Override
    public Product readProduct(ProductWithUuid product) {
        Optional<ProductE> productEOpt = productRepository.findById(product.getId().toString());
        ProductE productE = productEOpt.orElseThrow(() -> { throw productDoesNotExist(); });
        return new Product(productE);
    }
}
