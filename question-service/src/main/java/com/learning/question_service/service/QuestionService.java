package com.learning.question_service.service;

import com.learning.question_service.entity.Question;
import com.learning.question_service.repo.QuestionRepository;
import com.learning.question_service.entity.QuizDTO;
import com.learning.question_service.feignClient.QuizServiceClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizServiceClient quizServiceClient;

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(Integer id) {
        return questionRepository.findById(id);
    }

    public Question saveQuestion(Question question) {
        // Optionally validate that the quiz exists
        QuizDTO quizDTO = quizServiceClient.getQuizById(question.getQuizId());
        // Proceed with saving the question
        return questionRepository.save(question);
    }

    public Optional<Question> updateQuestion(Integer id, Question questionDetails) {
        return questionRepository.findById(id).map(existingQuestion -> {
            // Optionally validate that the updated quiz ID exists
            quizServiceClient.getQuizById(questionDetails.getQuizId());

            existingQuestion.setQuizId(questionDetails.getQuizId());
            existingQuestion.setQuestionText(questionDetails.getQuestionText());
            existingQuestion.setOptions(questionDetails.getOptions());
            existingQuestion.setAnswer(questionDetails.getAnswer());
            return questionRepository.save(existingQuestion);
        });
    }

    public boolean deleteQuestion(Integer id) {
        return questionRepository.findById(id).map(question -> {
            questionRepository.delete(question);
            return true;
        }).orElse(false);
    }
}
