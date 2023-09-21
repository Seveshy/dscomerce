package com.aula.devsuperior.dto;

import java.time.Instant;

import com.aula.devsuperior.entities.Payment;

public class PaymentDto {
    private Long id;
    private Instant moment;

    public PaymentDto(Long id, Instant moment) {
        this.id = id;
        this.moment = moment;
    }

    public PaymentDto(Payment entitiesPayment) {
        id = entitiesPayment.getId();
        moment = entitiesPayment.getMoment();
    }

    public Long getId() {
        return this.id;
    }

    public Instant getMoment() {
        return this.moment;
    }

}
