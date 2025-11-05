package com.suman.crm.controller;

import com.suman.crm.model.User;
import com.suman.crm.service.UserService;
import com.suman.crm.security.JwtUtil;
import com.suman.crm.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private com.suman.crm.security.CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        // You should add validations (email uniqueness etc.) in real app
        User user = new User();
        user.setFirst_name(req.getFirst_name());
        user.setLast_name(req.getLast_name());
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword()); // UserService will encode
        String roleStr = req.getRole() != null ? req.getRole().toUpperCase() : "ROLE_USER";
        user.setRole(User.Role.valueOf(roleStr)); // convert string to enum
        User saved = userService.createUser(user);
        return ResponseEntity.ok(Map.of("id", saved.getId(), "email", saved.getEmail(),"username",saved.getUsername()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        try {
           authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());
            User user = userService.findByUsername(req.getUsername());
Map<String, Object> claims = Map.of(
    "id", user.getId(),
    "username", user.getUsername(),
    "role", user.getRole().name()
);
        
        String token = jwtUtil.generateToken(userDetails.getUsername(),claims);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
