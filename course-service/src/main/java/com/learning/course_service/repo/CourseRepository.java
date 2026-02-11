package com.learning.course_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.learning.course_service.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    // You can add custom query methods here later if needed
}
