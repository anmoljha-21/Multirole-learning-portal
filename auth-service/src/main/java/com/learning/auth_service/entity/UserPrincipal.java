package com.learning.auth_service.entity;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

    private final Optional<User> user;

    public UserPrincipal(Optional<User> user) {
        this.user = user;
    }

   

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Unwrap User from Optional safely, or throw if absent
        User actualUser = user.orElseThrow(() -> new IllegalStateException("User not present"));
        
        Set<String> roles = actualUser.getRoles();  // now this works
        
        return roles.stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // Prefix roles with "ROLE_"
                    .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return user.orElseThrow(() -> new IllegalStateException("User not present")).getPassword();
    }

    @Override
    public String getUsername() {
        return user.orElseThrow(() -> new IllegalStateException("User not present")).getUsername();
    }
}