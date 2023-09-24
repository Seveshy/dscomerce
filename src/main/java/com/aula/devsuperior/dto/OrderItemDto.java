package com.aula.devsuperior.dto;

import com.aula.devsuperior.entities.OrderItem;

public class OrderItemDto {
    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;
    private String imgUrl;

    public OrderItemDto(Long productId, String name, Double price, Integer quantity, String imgUrl) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imgUrl = imgUrl;
    }

    public OrderItemDto(OrderItem entitiesOrderItem) {
        productId = entitiesOrderItem.getProduct().getId();
        name = entitiesOrderItem.getProduct().getName();
        price = entitiesOrderItem.getPrice();
        quantity = entitiesOrderItem.getQuantity();
        imgUrl = entitiesOrderItem.getProduct().getImgUrl();
    }

    public Long getProductId() {
        return this.productId;
    }

    public String getName() {
        return this.name;
    }

    public Double getPrice() {
        return this.price;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public Double getSubTotal() {
        return price * quantity;
    }

}
