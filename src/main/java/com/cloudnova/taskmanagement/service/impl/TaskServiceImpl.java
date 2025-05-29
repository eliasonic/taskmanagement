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

/**
 * Implementation of the TaskService interface.
 * Handles CRUD operations for tasks.
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    /**
     * Creates a new task based on the provided TaskRequestDto.
     * @param taskRequestDto The task data to be created.
     * @return TaskResponseDto The created task data.
     */
    @Override
    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {
        Task task = modelMapper.map(taskRequestDto, Task.class);
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, TaskResponseDto.class);
    }

    /**
     * Retrieves a task by its ID.
     * @param id The ID of the task to retrieve.
     * @return TaskResponseDto The task data.
     * @throws TaskNotFoundException If the task with the given ID is not found.
     */
    @Override
    public TaskResponseDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        return modelMapper.map(task, TaskResponseDto.class);
    }

    /**
     * Retrieves all tasks.
     * @return List<TaskResponseDto> A list of all task data.
     */
    @Override
    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(task -> modelMapper.map(task, TaskResponseDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Updates an existing task based on the provided ID and TaskRequestDto.
     * @param id The ID of the task to update.
     * @param taskRequestDto The updated task data.
     * @return TaskResponseDto The updated task data.
     * @throws TaskNotFoundException If the task with the given ID is not found.
     */
    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));

        modelMapper.map(taskRequestDto, existingTask);
        Task updatedTask = taskRepository.save(existingTask);

        return modelMapper.map(updatedTask, TaskResponseDto.class);
    }

    /**
     * Deletes a task by its ID.
     * @param id The ID of the task to delete.
     * @throws TaskNotFoundException If the task with the given ID is not found.
     */
    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
        taskRepository.delete(task);
    }
}