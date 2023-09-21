package com.aula.devsuperior.dto;

import com.aula.devsuperior.entities.OrderItem;

public class OrderItemDto {
    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;

    public OrderItemDto(Long productId, String name, Double price, Integer quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderItemDto(OrderItem entitiesOrderItem) {
        productId = entitiesOrderItem.getProduct().getId();
        name = entitiesOrderItem.getProduct().getName();
        price = entitiesOrderItem.getPrice();
        quantity = entitiesOrderItem.getQuantity();
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

    public Double getSubTotal() {
        return price * quantity;
    }

}
