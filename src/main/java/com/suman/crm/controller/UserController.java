package com.suman.crm.controller;

import com.suman.crm.DTO.RoleUpdateRequest;
import com.suman.crm.model.User;
import com.suman.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

@PatchMapping("/{id}/role")
public ResponseEntity<User> updateUserRole(
        @PathVariable Long id,
        @RequestBody RoleUpdateRequest request) {
    User updatedUser = userService.updateUserRole(id, request.getRole());
    return ResponseEntity.ok(updatedUser);
}


}
