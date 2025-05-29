package com.cloudnova.taskmanagement.exception;

/**
 * This class represents a custom exception that is thrown when a task is not found.
 */
public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
