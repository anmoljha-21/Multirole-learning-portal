package com.learning.auth_service.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.learning.auth_service.dto.UserDTO;

//com.learning.auth_service.feign.UserClient
@FeignClient(name = "user-service") // Ensure Eureka or static base-url setup
public interface UserClient {

 @PostMapping("/user/create")
 ResponseEntity<String> createUser(@RequestBody UserDTO userDTO);
}

