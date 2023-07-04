package com.aula.devsuperior.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.aula.devsuperior.dto.ProductDto;
import com.aula.devsuperior.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        ProductDto productDto = productService.findById(id);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll() {
        List<ProductDto> productDto = productService.findAll();
        return ResponseEntity.ok(productDto);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<ProductDto>> findAllPage(Pageable pageable) {
        Page<ProductDto> prodPage = productService.findAllPage(pageable);
        return ResponseEntity.ok(prodPage);
    }

    @PostMapping
    public ResponseEntity<ProductDto> insert(@RequestBody ProductDto productDto) {
        productDto = productService.insert(productDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productDto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(productDto);
    }
}