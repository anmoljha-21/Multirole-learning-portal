package com.learning.auth_service.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.auth_service.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
    
    Optional<User> findByEmail(String email);
    
    boolean existsByEmail(String email);

	Optional<User> findByUsername(String username);
}
