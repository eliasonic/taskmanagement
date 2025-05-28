package com.cloudnova.taskmanagement.service.impl;

import com.cloudnova.taskmanagement.dto.TaskRequestDto;
import com.cloudnova.taskmanagement.dto.TaskResponseDto;
import com.cloudnova.taskmanagement.exception.TaskNotFoundException;
import com.cloudnova.taskmanagement.model.Task;
import com.cloudnova.taskmanagement.repository.TaskRepository;
import com.cloudnova.taskmanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    @Override
    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {
        Task task = modelMapper.map(taskRequestDto, Task.class);
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, TaskResponseDto.class);
    }

    @Override
    public TaskResponseDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        return modelMapper.map(task, TaskResponseDto.class);
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(task -> modelMapper.map(task, TaskResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));

        modelMapper.map(taskRequestDto, existingTask);
        Task updatedTask = taskRepository.save(existingTask);

        return modelMapper.map(updatedTask, TaskResponseDto.class);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        taskRepository.delete(task);
    }
}