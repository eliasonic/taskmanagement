package com.cloudnova.taskmanagement.service;

import com.cloudnova.taskmanagement.dto.TaskRequest;
import com.cloudnova.taskmanagement.dto.TaskResponse;

import java.util.List;

/**
 * Service interface for managing tasks.
 * Defines methods for CRUD operations on tasks.
 */
public interface TaskService {
    TaskResponse createTask(TaskRequest taskRequestDto);
    TaskResponse getTaskById(Long id);
    List<TaskResponse> getAllTasks();
    TaskResponse updateTask(Long id, TaskRequest taskRequestDto);
    void deleteTask(Long id);
}