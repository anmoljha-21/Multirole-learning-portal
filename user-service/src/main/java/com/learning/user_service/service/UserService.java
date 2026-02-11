package com.learning.user_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learning.user_service.entity.User;
import com.learning.user_service.entity.UserDTO;
import com.learning.user_service.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;
    
    public String createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword("dummy"); // or handle null/encoded password appropriately
        user.setRoles(userDTO.getRoles());
        userRepository.save(user);
        return "User saved in user-service";
    }
    
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }


    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User updateUser(String username, User updatedUser) {
        User existingUser = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Apply only the fields that are allowed to be updated
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setRoles(updatedUser.getRoles());

        //existingUser.setFullName(updatedUser.getFullName());
        // Add more fields as needed

        return userRepository.save(existingUser); // This will perform an update
    }

}
