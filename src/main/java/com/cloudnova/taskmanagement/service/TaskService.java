package com.cloudnova.taskmanagement.service;

import com.cloudnova.taskmanagement.dto.TaskRequestDto;
import com.cloudnova.taskmanagement.dto.TaskResponseDto;

import java.util.List;

/**
 * Service interface for managing tasks.
 * Defines methods for CRUD operations on tasks.
 */
public interface TaskService {
    TaskResponseDto createTask(TaskRequestDto taskRequestDto);
    TaskResponseDto getTaskById(Long id);
    List<TaskResponseDto> getAllTasks();
    TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto);
    void deleteTask(Long id);
}