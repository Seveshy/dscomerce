package com.aula.devsuperior.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aula.devsuperior.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
