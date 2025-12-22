package com.learning.result_service.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer quizId;

    private Double score;

    private Double maxScore;

    @Temporal(TemporalType.TIMESTAMP)
    private Date takenOn;
}
