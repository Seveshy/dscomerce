package com.aula.devsuperior.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aula.devsuperior.dto.OrderDto;
import com.aula.devsuperior.entities.Order;
import com.aula.devsuperior.execptions.ResourceNotfoundException;
import com.aula.devsuperior.repositories.OrderRepository;

@Service
public class OrderService {
    
    @Autowired
    OrderRepository orderRepository;

    @Transactional(readOnly = true)
	public OrderDto findById(Long id) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotfoundException("Recurso nao encontrado"));
		return new OrderDto(order);
	}
}
