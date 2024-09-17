package com.cts.todo.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Task {
    @NotBlank(message = "Task name cannot be blank")
    private String taskName;
    @NotBlank(message = "Description cannot be blank")
    private String description;
    @NotNull(message = "taskStartDate cannot be null")
    private LocalDateTime taskStartDate;
    @NotNull(message = "taskEndDate cannot be null")
    private LocalDateTime taskEndDate;
    @NotBlank(message = "taskStatus cannot be blank")
    private String taskStatus;
    @NotNull(message = "totalEffortRequired cannot be null" )
    private String totalEffortRequired;

}
