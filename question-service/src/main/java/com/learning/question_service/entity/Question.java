package com.learning.question_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quizId;  // Foreign key reference to Quiz (optional, for reference)

    @Column(nullable = false)
    private String questionText;

    // Using ElementCollection to store options as a list of strings
    @ElementCollection
    @CollectionTable(name = "question_options", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "options")
    private List<String> options;

    @Column(nullable = false)
    private String answer; // could be index or text of the correct answer

}
