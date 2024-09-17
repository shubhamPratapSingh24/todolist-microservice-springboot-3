package com.cts.todo.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TodoApiErrorCode {

    DUPLICATE_DOCUMENT_EXCEPTION("Task with requested taskName already exists"),

    INVALID_TASK_EXCEPTION("Total effort required should be greater than 0");

    private String errorCode;

    private TodoApiErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @JsonValue
    public String getString() {
        return this.errorCode;
    }
}
