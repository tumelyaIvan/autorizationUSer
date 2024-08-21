package com.tumelya.autorization_user.service;

import com.tumelya.autorization_user.model.Gender;
import com.tumelya.autorization_user.model.User;
import com.tumelya.autorization_user.repository.GenderRepository;
import com.tumelya.autorization_user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final GenderRepository genderRepository;  // Добавляем репозиторий для работы с Gender
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, GenderRepository genderRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.genderRepository = genderRepository;  // Инициализация GenderRepository
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        Gender gender = genderRepository.findById(user.getGender().getId())
                .orElseThrow(() -> new IllegalArgumentException("Gender not found"));
        user.setGender(gender);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User updatedUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Gender gender = genderRepository.findById(updatedUser.getGender().getId())
                .orElseThrow(() -> new IllegalArgumentException("Gender not found"));
        user.setGender(gender);

        user.setLogin(updatedUser.getLogin());
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        user.setFullName(updatedUser.getFullName());

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("User not found with login: " + login));
    }
}
