package com.learning.course_service.entity;

import lombok.Data;

@Data
public class CourseWithInstructorDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer instructorId;
    private UserDTO instructor;  // UserDTO is returned from user-service Feign client

    // constructors, getters, setters
}

