package com.innoverasolutions.resource_management.service;

import com.innoverasolutions.resource_management.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void saveUser(User user);
    User authenticate(String email, String password);
    User getUserById(Long id);
    void updatePassword(Long userId, String newPassword);
    void deleteUserById(Long id);
    List<User> getAllUsers(); // Add this method
    Optional<User> findByEmail(String email);
}
