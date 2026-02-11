package com.learning.course_service.entity;


import java.util.Set;
import lombok.Data;

@Data
public class UserDTO {
    private Integer id;
    private String username;
    private String email;
    private Set<String> roles;
}

