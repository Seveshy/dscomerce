package com.aula.devsuperior.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aula.devsuperior.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}
