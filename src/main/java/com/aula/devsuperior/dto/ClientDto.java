package com.aula.devsuperior.dto;

import com.aula.devsuperior.entities.User;

public class ClientDto {
    private Long id;
    private String name;

    public ClientDto() {
    }

    public ClientDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ClientDto(User entiUser) {
        id = entiUser.getId();
        name = entiUser.getName();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

}
