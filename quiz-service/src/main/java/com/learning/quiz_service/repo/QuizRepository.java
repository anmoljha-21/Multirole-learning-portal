package com.learning.quiz_service.repo;

import com.learning.quiz_service.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
    // Custom query methods can be added here if needed
}
