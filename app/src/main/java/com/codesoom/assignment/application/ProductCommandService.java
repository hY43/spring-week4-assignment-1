package com.codesoom.assignment.application;

import com.codesoom.assignment.domain.Product;
import com.codesoom.assignment.domain.ProductRepository;
import org.springframework.stereotype.Service;

/**
 * 상품 정보의 변경을 책임집니다.
 */
@Service
public class ProductCommandService {

    private final ProductRepository productRepository;

    public ProductCommandService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * 상품을 저장하고 리턴합니다.
     */
    public Product saveProduct(final Product product) {
        return productRepository.save(product);
    }
}
