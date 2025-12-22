package com.learning.user_service.entity;

import java.util.Set;

import lombok.Data;

//com.learning.user_service.dto.UserDTO
@Data
public class UserDTO {
 private String username;
 private String email;
 private Set<String> roles;

 // Constructors, Getters and Setters
}
