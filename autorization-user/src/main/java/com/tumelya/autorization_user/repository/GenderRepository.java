package com.tumelya.autorization_user.repository;

import com.tumelya.autorization_user.model.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Integer> {
}
