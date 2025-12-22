package com.learning.course_service.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learning.course_service.config.FeignClientConfig;
import com.learning.course_service.entity.UserDTO;

@FeignClient(name = "user-service", configuration = FeignClientConfig.class)
public interface UserServiceClient {
    @GetMapping("/user/{id}")
    UserDTO getUserById(@PathVariable("id") Integer id);

    @GetMapping("/user/username/{username}")
    UserDTO getUserByUsername(@PathVariable("username") String username);
}

