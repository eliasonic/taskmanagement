package com.cloudnova.taskmanagement.controller;

import com.cloudnova.taskmanagement.dto.TaskRequest;
import com.cloudnova.taskmanagement.dto.TaskResponse;
import com.cloudnova.taskmanagement.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling task-related operations.
 * Provides endpoints for CRUD operations on tasks.
 */
@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Tag(name = "Task Management", description = "API for managing tasks")
public class TaskController {

    private final TaskService taskService;

    /**
     * Create a new task
     * @param taskRequestDto The task data to be created
     * @return ResponseEntity with the created task and HTTP status code
     */
    @PostMapping
    @Operation(summary = "Create a new task")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequestDto) {
        TaskResponse response = taskService.createTask(taskRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Get a task by ID
     * @param id The ID of the task to retrieve
     * @return ResponseEntity with the task data and HTTP status code
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a task by ID")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        TaskResponse response = taskService.getTaskById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Get all tasks
     * @return ResponseEntity with a list of all tasks and HTTP status code
     */
    @GetMapping
    @Operation(summary = "Get all tasks")
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> responses = taskService.getAllTasks();
        return ResponseEntity.ok(responses);
    }

    /**
     * Update a task
     * @param id The ID of the task to update
     * @param taskRequestDto The updated task data
     * @return ResponseEntity with the updated task data and HTTP status code
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a task")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest taskRequestDto) {
        TaskResponse response = taskService.updateTask(id, taskRequestDto);
        return ResponseEntity.ok(response);
    }

    /**
     * Delete a task
     * @param id The ID of the task to delete
     * @return ResponseEntity with HTTP status code
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}