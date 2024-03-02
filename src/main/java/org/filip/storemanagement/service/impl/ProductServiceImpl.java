package org.filip.storemanagement.service.impl;

import org.filip.storemanagement.repository.ProductE;
import org.filip.storemanagement.repository.ProductRepository;
import org.filip.storemanagement.service.Product;
import org.filip.storemanagement.service.ProductPatched;
import org.filip.storemanagement.service.ProductService;
import org.filip.storemanagement.service.ProductWithUuid;
import org.filip.storemanagement.service.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = Logger.getLogger(ProductServiceImpl.class.getName());

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        ProductE productE = new ProductE(product);
        productRepository.save(productE);
        LOGGER.log(Level.INFO, () -> "Product was saved successfully in DB");
        return product;
    }

    @Override
    public Product readProduct(ProductWithUuid product) {
        Optional<ProductE> productEOpt = productRepository.findById(product.getId().toString());
        ProductE productE = productEOpt.orElseThrow(() -> { throw logAndThrowNotFoundException(); });
        LOGGER.log(Level.INFO, () -> "Product fetched successfully from DB");
        return new Product(productE);
    }

    @Override
    public Product patchProduct(ProductPatched product) {
        Optional<ProductE> productEOpt = productRepository.findById(product.getId().toString());
        ProductE productE = productEOpt.orElseThrow(() -> { throw logAndThrowNotFoundException(); });
        patchEntity(product, productE);
        productRepository.save(productE);
        LOGGER.log(Level.INFO, () -> "Patched Product was saved successfully in DB");
        return new Product(productE);
    }

    private void patchEntity(ProductPatched product, ProductE entity) {
        if (product.getDescription() != null) {
            entity.setDescription(product.getDescription());
        }
        if (product.getName() != null) {
            entity.setName(product.getName());
        }
        if (product.getPrice() != null) {
            entity.setPrice(product.getPrice());
        }
        if (product.getQuantity() != null) {
            entity.setQuantity(product.getQuantity());
        }
    }

    private ProductNotFoundException logAndThrowNotFoundException() throws ProductNotFoundException {
        LOGGER.log(Level.SEVERE, "No product found for the given id");
        return new ProductNotFoundException();
    }
}
