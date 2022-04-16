package com.codesoom.assignment.controller;

import com.codesoom.assignment.application.ProductService;
import com.codesoom.assignment.domain.Product;
import com.codesoom.assignment.exception.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ProductControllerTest {

    ProductService service;
    ProductController controller;

    private Product PRODUCT;
    private final long ID = 1L;
    private final long NOT_FOUND_ID = 100000L;
    private final String MAKER = "KOREAN SHORT CAT";
    private final int PRICE = 20000;
    private final String NAME = "CAT FISHING ROD";
    private final String IMAGE = "https://www.zoostore.de/media/image/product/4598/sm/katzenspielzeug-katzenangel-spielangel-zum-zusammenschrauben-mit-heuschrecke~2.jpg";

    @BeforeEach
    void setUp() {
        service = mock(ProductService.class);
        controller = new ProductController(service);

        List<Product> products = new ArrayList<>();

        PRODUCT = new Product();
        PRODUCT.setId(ID);
        PRODUCT.setMaker(MAKER);
        PRODUCT.setPrice(PRICE);
        PRODUCT.setName(NAME);
        PRODUCT.setImage(IMAGE);
        products.add(PRODUCT);

        given(service.getProducts()).willReturn(products);
        given(service.getProduct(ID)).willReturn(PRODUCT);
        given(service.getProduct(NOT_FOUND_ID)).willThrow(ProductNotFoundException.class);
    }

    @Test
    void getProducts() {
        List<Product> products = controller.list();
        verify(service).getProducts();
        assertThat(products).isNotEmpty();
    }

    @Test
    void getEmptyProducts() {
        given(service.getProducts()).willReturn(new ArrayList<>());

        List<Product> products = controller.list();
        verify(service).getProducts();
        assertThat(products).isEmpty();
    }

    @Test
    void getProduct() {
        Product product = controller.detail(ID);
        verify(service).getProduct(ID);
        verifyProduct(product);
    }

    @Test
    void getNotFoundProduct() {
        assertThatThrownBy(() -> {
            controller.detail(NOT_FOUND_ID);
        }).isInstanceOf(ProductNotFoundException.class);

        verify(service).getProduct(NOT_FOUND_ID);
    }

    private void verifyProduct(Product product) {
        assertThat(product.getId()).isEqualTo(ID);
        assertThat(product.getMaker()).isEqualTo(MAKER);
        assertThat(product.getPrice()).isEqualTo(PRICE);
        assertThat(product.getImage()).isEqualTo(IMAGE);
        assertThat(product.getName()).isEqualTo(NAME);
    }
}