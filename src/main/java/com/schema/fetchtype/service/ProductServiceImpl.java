package com.schema.fetchtype.service;

import com.schema.fetchtype.dto.ProductDto;
import com.schema.fetchtype.model.Category;
import com.schema.fetchtype.model.Product;
import com.schema.fetchtype.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private final RestTemplateBuilder restTemplateBuilder;
    private final ProductRepo productRepo;

    ProductServiceImpl(RestTemplateBuilder restTemplateBuilder,
                       ProductRepo productRepo) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.productRepo = productRepo;
    }

    @Override
    public Product getProductById(Long id) {

        Optional<Product> response = productRepo.findById(id);
        if (response.isPresent()) {
            return response.get();
        }
        return null;

        /*
        ResponseEntity<ProductDto> objectResponseEntity =
                responseForEntity("https://fakestoreapi.com/products/{id}", HttpMethod.GET,null,ProductDto.class,id);

        if(objectResponseEntity.getBody()==null) {
            System.out.println("Fetching the product for id: " + id);
            return null;
        }
        return getProduct(objectResponseEntity.getBody());

         */
    }

    @Override
    public List<Product> getAllProducts() {

        return List.of();
    }

    // post request
    @Override
    public Product createProduct(Product product) {
        return productRepo.save(product);

    }

    private  <T> ResponseEntity<T> responseForEntity(String url, HttpMethod httpMethod, @Nullable Object request,
                                                     Class<T> responseType, Object... uriVariables) throws RestClientException {

        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    private Product getProduct(ProductDto productDto) {
        Product product = new Product();

        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getUrl());

        Category category = new Category();
        category.setId(productDto.getCategory().getId());
        category.setName(productDto.getCategory().getName());
        product.setCategory(category);

        return product;
    }

}
