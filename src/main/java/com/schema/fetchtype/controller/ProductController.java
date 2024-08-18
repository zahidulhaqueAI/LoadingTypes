package com.schema.fetchtype.controller;

import com.schema.fetchtype.dto.CategoryDto;
import com.schema.fetchtype.dto.ProductDto;
import com.schema.fetchtype.model.Category;
import com.schema.fetchtype.model.Product;
import com.schema.fetchtype.service.IProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ProductController {

    private final IProductService productService;

    ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {

        System.out.println("Pre ProductController called");
        Product product = productService.getProductById(productId);

       // Product product = productService.getProductById(productId);

        ProductDto productDto = getProductDto(product);
        System.out.println("Post ProductController called");

        return new ResponseEntity<>(productDto, HttpStatus.OK);

    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return null;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {

        System.out.println("Pre Post ProductController called");
        Product product = getProduct(productDto);
        Product responseProd = productService.createProduct(product);
        ProductDto responseProductDto = getProductDto(responseProd);

        return new ResponseEntity<>(responseProductDto, HttpStatus.CREATED);
    }

    private Product getProduct(ProductDto productDto) {
        Product product = new Product();

        product.setId(productDto.getId());
        product.setImageUrl(productDto.getUrl());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setUpdateAt(LocalDateTime.now());

        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setId(productDto.getCategory().getId());
            category.setDescription(productDto.getCategory().getDescription());
            category.setName(productDto.getCategory().getName());

            product.setCategory(category);
        }

        return product;
    }


    private ProductDto getProductDto(Product product) {

        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setUrl(product.getImageUrl());
        productDto.setDescription(product.getDescription());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());

        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }

        return productDto;
    }


}
