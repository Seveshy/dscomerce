package com.aula.devsuperior.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aula.devsuperior.entities.OrderItem;
import com.aula.devsuperior.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
