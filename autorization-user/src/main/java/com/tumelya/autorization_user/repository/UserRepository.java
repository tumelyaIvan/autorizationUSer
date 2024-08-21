package com.tumelya.autorization_user.repository;

import com.tumelya.autorization_user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
//    Optional<User> findByUserName(String userName);
}
