package com.learning.result_service.repo;

import com.learning.result_service.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Integer> {

    // Find all results by userId
    List<Result> findByUserId(Integer userId);

    // Find all results by quizId
    List<Result> findByQuizId(Integer quizId);
}
