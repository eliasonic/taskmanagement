package com.cloudnova.taskmanagement.service;

import com.cloudnova.taskmanagement.dto.TaskRequestDto;
import com.cloudnova.taskmanagement.dto.TaskResponseDto;

import java.util.List;

public interface TaskService {
    TaskResponseDto createTask(TaskRequestDto taskRequestDto);
    TaskResponseDto getTaskById(Long id);
    List<TaskResponseDto> getAllTasks();
    TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto);
    void deleteTask(Long id);
}