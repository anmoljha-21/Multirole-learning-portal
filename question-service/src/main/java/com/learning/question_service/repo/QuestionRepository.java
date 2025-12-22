package com.learning.question_service.repo;

import com.learning.question_service.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    
    // Optional: Find all questions by quizId
    List<Question> findByQuizId(Integer quizId);
}
