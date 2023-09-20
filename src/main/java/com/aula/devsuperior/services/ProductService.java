package com.aula.devsuperior.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aula.devsuperior.dto.CategoryDto;
import com.aula.devsuperior.dto.ProductDto;
import com.aula.devsuperior.dto.ProductMinDto;
import com.aula.devsuperior.entities.Category;
import com.aula.devsuperior.entities.Product;
import com.aula.devsuperior.execptions.DatabaseException;
import com.aula.devsuperior.execptions.ResourceNotfoundException;
import com.aula.devsuperior.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	// Somente leitura
	@Transactional(readOnly = true)
	public ProductDto findById(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotfoundException("Recurso nao encontrado"));
		return new ProductDto(product);
	}

	@Transactional(readOnly = true)
	public List<Product> findAll() {
		List<Product> prodList = productRepository.findAll();
		return prodList;
	}

	@Transactional(readOnly = true)
	public Page<ProductMinDto> findAllPage(String name, Pageable pageable) {
		Page<Product> prodList = productRepository.searchByName(name, pageable);
		return prodList.map(product -> new ProductMinDto(product));
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
		try {
			Product product = productRepository.getReferenceById(id);
			copyDtoToProduct(productDto, product);
			product = productRepository.save(product);
			return new ProductDto(product);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotfoundException("Recurso não encontrado.");
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delete(Long id) {
		try {
			productRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotfoundException("Recurso não encontrado.");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade referencial.");
		}
	}

	private void copyDtoToProduct(ProductDto productDto, Product product) {
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setImgUrl(productDto.getImgUrl());
		product.getCategories().clear();
		for (CategoryDto categoryDto : productDto.getCategories()) {
			Category category = new Category();
			category.setId(categoryDto.getId());
			product.getCategories().add(category);
		}
	}
}
