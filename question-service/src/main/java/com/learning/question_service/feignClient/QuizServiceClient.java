package com.learning.question_service.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learning.question_service.config.FeignClientConfig;
import com.learning.question_service.entity.QuizDTO;

@FeignClient(name = "quiz-service", configuration = FeignClientConfig.class)
public interface QuizServiceClient {
    @GetMapping("/quizzes/{id}")
    QuizDTO getQuizById(@PathVariable("id") Integer id);
}

