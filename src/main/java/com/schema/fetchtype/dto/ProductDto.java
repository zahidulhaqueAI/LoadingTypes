package com.schema.fetchtype.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {

    private Long id;
    private String title;
    private String description;
    private double price;
    private String url;

    private CategoryDto category;
}
