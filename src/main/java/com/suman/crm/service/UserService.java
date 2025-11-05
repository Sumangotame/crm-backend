package com.suman.crm.service;

import com.suman.crm.model.User;
import com.suman.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            user.setRole(updatedUser.getRole());
            return userRepository.save(user);
        }).orElseGet(() -> {
            updatedUser.setId(id);
            return userRepository.save(updatedUser);
        });
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

   public User findByUsername(String username) {
    return userRepository.findByUsername(username)
            .orElse(null); // or throw new UsernameNotFoundException("User not found")
}

public User updateUserRole(Long id, String roleStr) {
    User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    user.setRole(User.Role.valueOf(roleStr));
    return userRepository.save(user);
}

}
