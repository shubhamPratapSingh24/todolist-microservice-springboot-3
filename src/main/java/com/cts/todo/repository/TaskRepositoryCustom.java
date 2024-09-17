package com.cts.todo.repository;

import com.cts.todo.entity.Task;

import java.util.List;

public interface TaskRepositoryCustom {

    List<Task> getTaskByTaskName(String taskName);

    Boolean updateTaskStatus(String taskName ,String taskStatus);

    Boolean updateTaskStatus(String taskStatus);

}
