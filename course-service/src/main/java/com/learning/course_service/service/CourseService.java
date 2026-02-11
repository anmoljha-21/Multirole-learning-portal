package com.learning.course_service.service;

import com.learning.course_service.entity.Course;
import com.learning.course_service.entity.CourseWithInstructorDTO;
import com.learning.course_service.entity.UserDTO;
import com.learning.course_service.FeignClient.UserServiceClient;
import com.learning.course_service.repo.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserServiceClient userServiceClient;

    // Get all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get a course by id
    public Optional<Course> getCourseById(Integer id) {
        return courseRepository.findById(id);
    }

    // New method to get course + instructor info
    public Optional<CourseWithInstructorDTO> getCourseWithInstructorById(Integer id) {
        return courseRepository.findById(id)
                .map(course -> {
                    UserDTO instructor = null;
                    try {
                        instructor = userServiceClient.getUserById(course.getInstructorId());
                    } catch (Exception e) {
                        // handle exception if user-service is down or instructor not found
                    }

                    CourseWithInstructorDTO dto = new CourseWithInstructorDTO();
                    dto.setId(course.getId());
                    dto.setTitle(course.getTitle());
                    dto.setDescription(course.getDescription());
                    dto.setInstructorId(course.getInstructorId());
                    dto.setInstructor(instructor);
                    return dto;
                });
    }

    // Save a new course
    public Course createCourse(Course course) {
        // Fetch user from User Service
        UserDTO instructor;
        try {
            instructor = userServiceClient.getUserById(course.getInstructorId());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch instructor from user-service", e);
        }

        // Validate instructor role (case-insensitive)
        if (instructor == null || 
            instructor.getRoles().stream()
                .noneMatch(role -> role.equalsIgnoreCase("INSTRUCTOR"))) {
            throw new IllegalArgumentException("Instructor ID is invalid or user is not an instructor");
        }

        // Save course
        return courseRepository.save(course);
    }



    // Update an existing course
    public Optional<Course> updateCourse(Integer id, Course updatedCourse) {
        return courseRepository.findById(id)
                .map(existingCourse -> {
                    existingCourse.setTitle(updatedCourse.getTitle());
                    existingCourse.setDescription(updatedCourse.getDescription());
                    existingCourse.setInstructorId(updatedCourse.getInstructorId());
                    // Add other fields you want to update here
                    return courseRepository.save(existingCourse);
                });
    }

    // Delete course by id
    public boolean deleteCourse(Integer id) {
        return courseRepository.findById(id)
                .map(course -> {
                    courseRepository.delete(course);
                    return true;
                }).orElse(false);
    }
}
