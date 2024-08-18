package com.schema.fetchtype.repository;

import com.schema.fetchtype.model.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    @Transactional
    public void findProductByPriceBetween() {
        List<Product> products = productRepo.findProductByPriceBetween(7500.0, 42000D);

        for (Product product : products) {
            System.out.println(product.getPrice() + " " + product.getDescription()) ;
        }
    }

    @Test
    @Transactional
    public void findAllByOrderByPriceDesc() {
        List<Product> products = productRepo.findAllByOrderByPriceDesc();

        for (Product product : products) {
            System.out.println(product.getPrice() + " " + product.getDescription()) ;
        }
    }

    @Test
    @Transactional
    public void findProductNameByProductId() {
        String name = productRepo.findProductNameByProductId(1009L);
        System.out.println(name);
    }

    @Test
    @Transactional
    public void findCategoryNameByProductId() {
        String name = productRepo.findCategoryNameByProductId(1009L);
        System.out.println(name);
    }
}