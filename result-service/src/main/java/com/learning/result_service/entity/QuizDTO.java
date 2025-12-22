package com.learning.result_service.entity;

import lombok.Data;

@Data
public class QuizDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer courseId;
}
