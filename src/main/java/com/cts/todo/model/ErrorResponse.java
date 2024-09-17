package com.cts.todo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String errorCode;
    private String errorMessage;
    private String developerMessage;
    private Date timestamp;

    @Builder
    public ErrorResponse(
            String errorCode,
            String errorMessage,
            String developerMessage) {
        this.timestamp = new Date();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.developerMessage = developerMessage;
    }
}