package com.suman.crm.security;

import com.suman.crm.model.User;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.suman.crm.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found with name:" + username));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user)

        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String role = user.getRole() != null ? user.getRole().name() : "USER";
        if (!role.startsWith("ROLE_")) {
            role = "ROLE_" + role;
        }
        return List.of(new SimpleGrantedAuthority(role));
    }
}
