package com.cloudnova.taskmanagement.controller;

import com.cloudnova.taskmanagement.dto.TaskRequestDto;
import com.cloudnova.taskmanagement.dto.TaskResponseDto;
import com.cloudnova.taskmanagement.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@Tag(name = "Task Management", description = "API for managing tasks")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Create a new task")
    public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody TaskRequestDto taskRequestDto) {
        TaskResponseDto response = taskService.createTask(taskRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a task by ID")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {
        TaskResponseDto response = taskService.getTaskById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all tasks")
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        List<TaskResponseDto> responses = taskService.getAllTasks();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a task")
    public ResponseEntity<TaskResponseDto> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequestDto taskRequestDto) {
        TaskResponseDto response = taskService.updateTask(id, taskRequestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}