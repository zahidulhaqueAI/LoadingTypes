package com.schema.fetchtype.service;

import com.schema.fetchtype.model.Product;

import java.util.List;

public interface IProductService {

    public Product getProductById(Long id);
    public List<Product> getAllProducts();
    public Product createProduct(Product product);
}
