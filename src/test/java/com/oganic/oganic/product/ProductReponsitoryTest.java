package com.oganic.oganic.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import com.oganic.oganic.category.Category;
import com.oganic.oganic.category.CategoryRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application-test.properties")
 public class ProductReponsitoryTest {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category1;
    private Category category2;

    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    void setup() {
        category1 = Category.builder()
                            .name("category1")
                            .build();

        category2 = Category.builder()
                            .name("category2")
                            .build();

        categoryRepository.save(category1);
        categoryRepository.save(category2);

        // save product

        Timestamp createdAt1 = Timestamp.valueOf("2024-12-02 07:00:00");
        Timestamp createdAt2 = Timestamp.valueOf("2025-01-01 09:00:00");
        Timestamp createdAt3 = Timestamp.valueOf("2024-11-01 09:00:00");
        product1 = Product.builder()
                    .name("Apple")
                    .price(19.9)
                    .createdAt(createdAt1)
                    .discount(0.1)
                    .category(category1)
                    .build();
        product2 = Product.builder()
                        .name("Banana")
                        .price(10.0)
                        .createdAt(createdAt2)
                        .category(category1)
                        .build();
        product3 = Product.builder()
                        .name("Bana")
                        .price(20.0)
                        .discount(0.2)
                        .createdAt(createdAt3)
                        .category(category2)
                        .build();
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
    }
    

    @Test
    public void testGetProducts() {
        List<Product> products = productRepository.findAll();
        assertEquals(3, products.size());
        assertEquals(products.get(0), product1);
        assertEquals(products.get(1), product2);
    }

    @Test
    public void testSearchProductByName() {
        Pageable pageable = PageRequest.of(0, 1);
        Page<Product> pageProduct = productRepository.search(null, "ana", pageable);
        assertEquals(2, pageProduct.getTotalElements());
        List<Product> products = pageProduct.getContent();
        assertEquals(1, products.size());
        assertEquals(products.get(0), product2);
    }

    @Test
    public void testSearchProductByCategory() {
        Pageable pageable = PageRequest.of(0, 1);
        Page<Product> pageProduct = productRepository.search(category2.getId(),null, pageable);
        assertEquals(1, pageProduct.getTotalElements());
        List<Product> products = pageProduct.getContent();
        assertEquals(1, products.size());
        assertEquals(products.get(0), product3);
    }

    @Test
    public void testGetDiscountProducts() {
        List<Product> products = productRepository.findDiscount();
        assertEquals(2, products.size());
        // check product2 not in products
        assertFalse(products.contains(product2));
        // product1,3 in products
        assertTrue(products.contains(product1));
        assertTrue(products.contains(product3));
        assertEquals(product1.getDiscount(), products.get(0).getDiscount());
    }
}
