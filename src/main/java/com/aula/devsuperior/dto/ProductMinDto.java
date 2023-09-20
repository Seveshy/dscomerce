package com.aula.devsuperior.dto;

import com.aula.devsuperior.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductMinDto {

    private Long id;
    private String name;
    private Double price;
    private String imgUrl;

    public ProductMinDto() {
    }

    public ProductMinDto(Long id, String name, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imgUrl = imgUrl;
    }
    

     public ProductMinDto(Product entity) {
        id = entity.getId();
        name = entity.getName();
        price = entity.getPrice();
        imgUrl = entity.getImgUrl();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Double getPrice() {
        return this.price;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

}
