package com.learning.quiz_service.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learning.quiz_service.config.FeignClientConfig;
import com.learning.quiz_service.entity.CourseDTO;

@FeignClient(name = "course-service", configuration = FeignClientConfig.class)
public interface CourseServiceClient {

    @GetMapping("/courses/{id}")
    CourseDTO getCourseById(@PathVariable("id") Integer id);
}
