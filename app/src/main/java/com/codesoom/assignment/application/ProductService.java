package com.codesoom.assignment.application;

import javax.transaction.Transactional;

import com.codesoom.assignment.domain.ProductRepository;
import com.codesoom.assignment.domain.Product;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(final Product product) {
        return productRepository.save(product);
    }
}
