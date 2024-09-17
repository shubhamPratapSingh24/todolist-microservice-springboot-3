package com.cts.todo.advice;

import com.cts.todo.exception.InvalidTaskException;
import com.cts.todo.model.ErrorResponse;
import com.cts.todo.model.TodoApiErrorCode;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TodoServiceExceptionAdvice {

    @ExceptionHandler(DuplicateKeyException.class)
    public final ResponseEntity<ErrorResponse> handleConstraintViolationException(DuplicateKeyException e) {
        ErrorResponse apiError = ErrorResponse.builder()
                .errorMessage(e.getMessage())
                .developerMessage(TodoApiErrorCode.DUPLICATE_DOCUMENT_EXCEPTION.getString())
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.PRECONDITION_FAILED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler(InvalidTaskException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidTaskException(InvalidTaskException e) {
        ErrorResponse apiError = ErrorResponse.builder()
                .errorMessage(e.getMessage())
                .developerMessage(TodoApiErrorCode.INVALID_TASK_EXCEPTION.getString())
                .build();

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
