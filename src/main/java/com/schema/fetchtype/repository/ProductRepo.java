package com.schema.fetchtype.repository;

import com.schema.fetchtype.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    // fetch all the products price between low and high
    List<Product> findProductByPriceBetween(Double lower, Double higher);

    // fetch the products based on descending price
    List<Product> findAllByOrderByPriceDesc();

    // get product by id
    @Query("SELECT p.description FROM Product p WHERE p.id =?1")
    String findProductNameByProductId(Long id);

    // get Category Name from product id
    @Query("SELECT c.name from Category c join Product p ON c.id = p.category.id where p.id=:productId")
    String findCategoryNameByProductId(Long productId);
}
