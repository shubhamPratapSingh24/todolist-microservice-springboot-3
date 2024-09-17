package com.cts.todo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class InvalidTaskException extends RuntimeException{

    private String message;
    private Throwable cause;

    public InvalidTaskException(String message) {
    }
}
