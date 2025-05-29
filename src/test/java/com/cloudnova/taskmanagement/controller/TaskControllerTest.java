package com.cloudnova.taskmanagement.controller;

import com.cloudnova.taskmanagement.dto.TaskRequestDto;
import com.cloudnova.taskmanagement.model.Task;
import com.cloudnova.taskmanagement.repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TaskRepository taskRepository;

    @AfterEach
    void tearDown() {
        taskRepository.deleteAll();
    }

    private Task createTestTask() {
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setStatus("PENDING");
        task.setDueDate(LocalDateTime.now().plusDays(1));
        return task;
    }

    @Test
    void createTask_ShouldReturnCreatedTask() throws Exception {
        TaskRequestDto request = new TaskRequestDto(
                "Test Task",
                "Test Description",
                "PENDING",
                LocalDateTime.now().plusDays(1)
        );

        mockMvc.perform(post("/api/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.title", is("Test Task")))
                .andExpect(jsonPath("$.status", is("PENDING")));
    }

    @Test
    void getTaskById_ShouldReturnTask() throws Exception {
        Task savedTask = taskRepository.save(createTestTask());

        mockMvc.perform(get("/api/v1/tasks/" + savedTask.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(savedTask.getId().intValue())))
                .andExpect(jsonPath("$.title", is("Test Task")));
    }

    @Test
    void getTaskById_ShouldReturn404WhenNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/tasks/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllTasks_ShouldReturnTaskList() throws Exception {
        taskRepository.save(createTestTask());
        taskRepository.save(createTestTask());

        mockMvc.perform(get("/api/v1/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Test Task")));
    }

    @Test
    void updateTask_ShouldUpdateTask() throws Exception {
        Task savedTask = taskRepository.save(createTestTask());
        TaskRequestDto updateRequest = new TaskRequestDto(
                "Updated Task",
                null,
                "DONE",
                savedTask.getDueDate()
        );

        mockMvc.perform(put("/api/v1/tasks/" + savedTask.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Updated Task")))
                .andExpect(jsonPath("$.status", is("DONE")));
    }

    @Test
    void deleteTask_ShouldReturnNoContent() throws Exception {
        Task savedTask = taskRepository.save(createTestTask());

        mockMvc.perform(delete("/api/v1/tasks/" + savedTask.getId()))
                .andExpect(status().isNoContent());

        assertFalse(taskRepository.existsById(savedTask.getId()));
    }
}