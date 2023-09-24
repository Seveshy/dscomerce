package com.aula.devsuperior.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aula.devsuperior.dto.OrderDto;
import com.aula.devsuperior.dto.OrderItemDto;
import com.aula.devsuperior.entities.Order;
import com.aula.devsuperior.entities.OrderItem;
import com.aula.devsuperior.entities.OrderStatus;
import com.aula.devsuperior.entities.Product;
import com.aula.devsuperior.execptions.ResourceNotfoundException;
import com.aula.devsuperior.repositories.OrderItemRepository;
import com.aula.devsuperior.repositories.OrderRepository;
import com.aula.devsuperior.repositories.ProductRepository;

@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	OrderItemRepository orderItemRepository;

	@Transactional(readOnly = true)
	public OrderDto findById(Long id) {
		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotfoundException("Recurso nao encontrado"));
		return new OrderDto(order);
	}

	@Transactional
	public OrderDto insert(OrderDto orderDto) {
		Order order = new Order();

		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITING_PAYMENT);

		for (OrderItemDto itemDto : orderDto.getItems()) {
			Product product = productRepository.getReferenceById(itemDto.getProductId());
			OrderItem orderItem = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
			order.getItems().add(orderItem);
		}

		orderRepository.save(order);
		orderItemRepository.saveAll(order.getItems());

		return new OrderDto(order);
	}
}
