package com.learning.result_service.controller;

import com.learning.result_service.entity.Result;
import com.learning.result_service.service.ResultService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    // Get all results
    @GetMapping
    public ResponseEntity<List<Result>> getAllResults() {
        List<Result> results = resultService.getAllResults();
        return ResponseEntity.ok(results);
    }

    // Get result by ID
    @GetMapping("/{id}")
    public ResponseEntity<Result> getResultById(@PathVariable Integer id) {
        Optional<Result> result = resultService.getResultById(id);
        return result.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    // Get results by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Result>> getResultsByUserId(@PathVariable Integer userId) {
        List<Result> results = resultService.getResultsByUserId(userId);
        return ResponseEntity.ok(results);
    }

    // Create a new result
    @PostMapping
    public ResponseEntity<Result> createResult(@RequestBody Result result) {
        Result savedResult = resultService.saveResult(result);
        return ResponseEntity.ok(savedResult);
    }

    // Update an existing result
    @PutMapping("/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable Integer id, @RequestBody Result resultDetails) {
        Optional<Result> updatedResult = resultService.updateResult(id, resultDetails);
        return updatedResult.map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
    }

    // Delete result by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Integer id) {
        boolean deleted = resultService.deleteResult(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
