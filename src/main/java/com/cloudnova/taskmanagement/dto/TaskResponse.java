package com.cloudnova.taskmanagement.dto;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for Task entity.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private String status;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}