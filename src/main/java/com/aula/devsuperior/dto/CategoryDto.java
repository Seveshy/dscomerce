package com.aula.devsuperior.dto;

import com.aula.devsuperior.entities.Category;

public class CategoryDto {
    private Long id;
    private String name;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryDto(Category entiCategory) {
        id = entiCategory.getId();
        name = entiCategory.getName();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

}
