package com.learning.auth_service.dto;

import java.util.Set;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
  //  private String email;
    private String password;
    private Set<String> roles;
}
