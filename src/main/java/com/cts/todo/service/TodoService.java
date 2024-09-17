package com.cts.todo.service;

import com.cts.todo.model.Task;

import java.util.List;

public interface TodoService {
    Task addTask(Task task);

    List<Task> addTaskList(List<Task> tasks);

    List<Task> getAllTasks();

    List<Task> getTaskByName(String taskName);

    Task updateTaskStatus(String taskName ,String taskStatus);
}
