package com.cts.todo.controller;

import com.cts.todo.model.Task;
import com.cts.todo.service.TodoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class TodoController {

    @Autowired
    TodoServiceImpl service;

    @PutMapping(path = "/addTask")
    public Task addTask(@RequestBody @Valid Task task){

        return service.addTask(task);
    }

    @PutMapping(path = "/add-list")
    public List<Task> addTask(@RequestBody @Valid List<Task> taskList){

        return service.addTaskList(taskList);
    }

    @GetMapping(path = "/list/all")
    public List<Task> getAllTasks(){
        return service.getAllTasks();
    }

    @GetMapping(path = "/list/{taskName}")
    public List<Task> getTaskByName(@PathVariable String taskName){
        return service.getTaskByName(taskName);
    }

    @PostMapping(path = "/update/{taskName}/{taskStatus}")
    public Task updateTaskStatus(@PathVariable String taskName, @PathVariable String taskStatus){
        return service.updateTaskStatus( taskName, taskStatus);
    }

    @PostMapping(path = "/update/{taskStatus}")
    public Boolean updateTaskStatus( @PathVariable String taskStatus){
        return service.updateTaskStatus( taskStatus);
    }

}
