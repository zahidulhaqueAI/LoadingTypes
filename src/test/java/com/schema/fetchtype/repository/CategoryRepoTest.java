package com.schema.fetchtype.repository;

import com.schema.fetchtype.model.Category;
import com.schema.fetchtype.model.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class CategoryRepoTest {

    @Autowired
    private CategoryRepo repo;

    @Test
    @Transactional
    public  void getCategoryById() {
        Optional<Category> c = repo.findById(25L);
        Category cat = c.get();

        for(Product p : cat.getProducts()) {
            System.out.println(p.getDescription());
        }
    }
}