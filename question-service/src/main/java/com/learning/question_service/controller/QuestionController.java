package com.learning.question_service.controller;

import com.learning.question_service.entity.Question;

import com.learning.question_service.service.QuestionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController {
	
	 @GetMapping("/public/info")
	    public ResponseEntity<String> publicInfo() {
	        return ResponseEntity.ok("This is a public endpoint. No authentication needed.");
	    }
    @Autowired
    private QuestionService questionService;
    // Get all questions
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    // Get question by ID
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Integer id) {
        Optional<Question> question = questionService.getQuestionById(id);
        return question.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    // Create new question
    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question savedQuestion = questionService.saveQuestion(question);
        return ResponseEntity.ok(savedQuestion);
    }

    // Update existing question
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Integer id, @RequestBody Question questionDetails) {
        Optional<Question> updatedQuestion = questionService.updateQuestion(id, questionDetails);
        return updatedQuestion.map(ResponseEntity::ok)
                              .orElse(ResponseEntity.notFound().build());
    }

    // Delete question by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer id) {
        boolean deleted = questionService.deleteQuestion(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
