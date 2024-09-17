package com.cts.todo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class TaskUpdateFailedException extends RuntimeException{
    private String message;
    private Throwable cause;

    public TaskUpdateFailedException(String message) {
    }
}
