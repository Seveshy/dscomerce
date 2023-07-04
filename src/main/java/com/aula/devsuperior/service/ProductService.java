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

    @Transactional
    public ProductDto insert(ProductDto productDto) {
        Product product = new Product();
        copyDtoToProduct(productDto, product);

        product = productRepository.save(product);
        return new ProductDto(product);
    }

    @Transactional
    public ProductDto update(Long id, ProductDto productDto) {
        Product product = productRepository.getReferenceById(id);
        copyDtoToProduct(productDto, product);
        product = productRepository.save(product);
        return new ProductDto(product);
    }

    @Transactional()
    public void delete(Long id) {
        productRepository.deleteById(id);
    }


    private void copyDtoToProduct(ProductDto productDto, Product product) {
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImgUrl(productDto.getImgUrl());
    }
}
