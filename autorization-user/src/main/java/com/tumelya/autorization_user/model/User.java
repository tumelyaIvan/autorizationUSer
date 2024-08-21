package com.tumelya.autorization_user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "client")
public class User {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    @Size(max = 50)
    private String login;

    @Column(length = 100, nullable = false)
    @Size(max = 100)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*]).{7,20}$", message = "Invalid password format")
    private String password;

    @Column(length = 256)
    @Size(max = 256)
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
