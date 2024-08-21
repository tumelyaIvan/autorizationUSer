package com.tumelya.autorization_user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import org.springframework.data.annotation.Id;

@Entity
public class Gender {
    @jakarta.persistence.Id
    @Id
    private Integer id;

    @Column(length = 50, nullable = false)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
