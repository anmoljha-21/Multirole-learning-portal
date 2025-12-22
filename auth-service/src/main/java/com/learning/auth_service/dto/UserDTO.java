package com.learning.auth_service.dto;

import java.util.Set;

import lombok.Data;

//com.learning.auth_service.dto.UserDTO
@Data
public class UserDTO {
 private String username;
 private String email;
 private Set<String> roles;

 // Constructors
 public UserDTO() {}
 public UserDTO(String username, String email) {
     this.username = username;
     this.email = email;
 }

 // Getters & setters
}
