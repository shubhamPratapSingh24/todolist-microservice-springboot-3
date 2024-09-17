package com.cts.todo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class NoTaskFoundException extends RuntimeException{

    private final String message;
    private final Throwable cause;
}
