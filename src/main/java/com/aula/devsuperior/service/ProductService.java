package com.aula.devsuperior.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aula.devsuperior.dto.ProductDto;
import com.aula.devsuperior.entities.Product;
import com.aula.devsuperior.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    // Somente leitura
    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id).get();
        return new ProductDto(product);
    }

    @Transactional(readOnly = true)
    public List<ProductDto> findAll() {
        List<Product> prodList = productRepository.findAll();
        return prodList.stream().map(product -> new ProductDto(product)).toList();
    }

     @Transactional(readOnly = true)
    public Page<ProductDto> findAllPage(Pageable pageable) {
        Page<Product> prodList = productRepository.findAll(pageable);
        return prodList.map(product -> new ProductDto(product));
    }
}
