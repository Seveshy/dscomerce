package com.aula.devsuperior.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.aula.devsuperior.entities.Order;
import com.aula.devsuperior.entities.OrderItem;
import com.aula.devsuperior.entities.OrderStatus;

public class OrderDto {
    private Long id;
    private Instant moment;
    private OrderStatus status;

    private ClientDto client;
    private PaymentDto payment;

    private List<OrderItemDto> items = new ArrayList<>();

    public OrderDto(Long id, Instant moment, OrderStatus status, ClientDto client, PaymentDto payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
    }

    public OrderDto(Order entitiesOrder) {
        id = entitiesOrder.getId();
        moment = entitiesOrder.getMoment();
        status = entitiesOrder.getStatus();
        client = new ClientDto(entitiesOrder.getClient());
        payment = (entitiesOrder.getPayment() == null) ? null : new PaymentDto(entitiesOrder.getPayment());
        for (OrderItem item : entitiesOrder.getItems()) {
            OrderItemDto itemDto = new OrderItemDto(item);
            items.add(itemDto);
        }
    }

    public Long getId() {
        return this.id;
    }

    public Instant getMoment() {
        return this.moment;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public ClientDto getClient() {
        return this.client;
    }

    public PaymentDto getPayment() {
        return this.payment;
    }

    public List<OrderItemDto> getItems() {
        return this.items;
    }

    public Double getTotal() {
        double sum = 0.0;
        for (OrderItemDto item : items) {
            sum = sum + item.getSubTotal();
        } 

        return sum;
    }

}
