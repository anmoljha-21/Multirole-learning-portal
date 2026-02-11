package com.learning.quiz_service.service;

import com.learning.quiz_service.FeignClient.CourseServiceClient;
import com.learning.quiz_service.FeignClient.UserServiceClient;
import com.learning.quiz_service.entity.CourseDTO;
import com.learning.quiz_service.entity.Quiz;
import com.learning.quiz_service.entity.UserDTO;
import com.learning.quiz_service.repo.QuizRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CourseServiceClient courseServiceClient;

    @Autowired
    private UserServiceClient userServiceClient;

    // Get all quizzes
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    // Get quiz by ID
    public Optional<Quiz> getQuizById(Integer id) {
        return quizRepository.findById(id);
    }

    // Create or save quiz
    public Quiz saveQuiz(Quiz quiz) {
        // Validate course
        CourseDTO courseDTO = courseServiceClient.getCourseById(quiz.getCourseId());
        if (courseDTO == null) {
            throw new RuntimeException("Invalid course ID: " + quiz.getCourseId());
        }

        // Validate instructor
        UserDTO instructorDTO = userServiceClient.getUserById(quiz.getInstructorId());
        if (instructorDTO == null || !instructorDTO.getRoles().stream().anyMatch(role -> role.equalsIgnoreCase("INSTRUCTOR"))) {
            throw new RuntimeException("Invalid or unauthorized instructor ID: " + quiz.getInstructorId());
        }

        return quizRepository.save(quiz);
    }

    // Update quiz
    public Optional<Quiz> updateQuiz(Integer id, Quiz quizDetails) {
        return quizRepository.findById(id).map(existingQuiz -> {
            existingQuiz.setTitle(quizDetails.getTitle());
            existingQuiz.setDescription(quizDetails.getDescription());

            // Optional: Validate again if courseId/instructorId changed
            if (!quizDetails.getCourseId().equals(existingQuiz.getCourseId())) {
                CourseDTO courseDTO = courseServiceClient.getCourseById(quizDetails.getCourseId());
                if (courseDTO == null) {
                    throw new RuntimeException("Invalid course ID: " + quizDetails.getCourseId());
                }
                existingQuiz.setCourseId(quizDetails.getCourseId());
            }

            if (!quizDetails.getInstructorId().equals(existingQuiz.getInstructorId())) {
                UserDTO instructorDTO = userServiceClient.getUserById(quizDetails.getInstructorId());
                if (instructorDTO == null || !instructorDTO.getRoles().stream().anyMatch(role -> role.equalsIgnoreCase("INSTRUCTOR"))) {
                    throw new RuntimeException("Invalid or unauthorized instructor ID: " + quizDetails.getInstructorId());
                }
                existingQuiz.setInstructorId(quizDetails.getInstructorId());
            }

            return quizRepository.save(existingQuiz);
        });
    }
    
    // Delete quiz
    public boolean deleteQuiz(Integer id) {
        if (quizRepository.existsById(id)) {
            quizRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
