package com.learning.result_service.service;

import com.learning.result_service.entity.Result;
import com.learning.result_service.entity.QuizDTO;
import com.learning.result_service.entity.UserDTO;
import com.learning.result_service.feignClient.QuizServiceClient;
import com.learning.result_service.feignClient.UserServiceClient;
import com.learning.result_service.repo.ResultRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private QuizServiceClient quizServiceClient;

    @Autowired
    private UserServiceClient userServiceClient;

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    public Optional<Result> getResultById(Integer id) {
        return resultRepository.findById(id);
    }

    public List<Result> getResultsByUserId(Integer userId) {
        return resultRepository.findByUserId(userId);
    }

    public Result saveResult(Result result) {
        // Optionally validate if quiz and user exist before saving
        QuizDTO quiz = quizServiceClient.getQuizById(result.getQuizId());
        UserDTO user = userServiceClient.getUserById(result.getUserId());

        // You can add validation or logging here if needed

        return resultRepository.save(result);
    }

    public Optional<Result> updateResult(Integer id, Result resultDetails) {
        return resultRepository.findById(id)
                .map(existingResult -> {
                    existingResult.setUserId(resultDetails.getUserId());
                    existingResult.setQuizId(resultDetails.getQuizId());
                    existingResult.setScore(resultDetails.getScore());
                    existingResult.setMaxScore(resultDetails.getMaxScore());
                    existingResult.setTakenOn(resultDetails.getTakenOn());

                    // Optionally validate updated quiz and user IDs
                    quizServiceClient.getQuizById(resultDetails.getQuizId());
                    userServiceClient.getUserById(resultDetails.getUserId());

                    return resultRepository.save(existingResult);
                });
    }

    public boolean deleteResult(Integer id) {
        return resultRepository.findById(id)
                .map(result -> {
                    resultRepository.delete(result);
                    return true;
                }).orElse(false);
    }
}
