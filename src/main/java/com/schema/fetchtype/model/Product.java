package com.schema.fetchtype.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Product extends BaseModel {

    private Long id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;
}
