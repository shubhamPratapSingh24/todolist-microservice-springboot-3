package com.cts.todo.service;

import com.cts.todo.exception.TaskUpdateFailedException;
import com.cts.todo.mapper.MapperUtil;
import com.cts.todo.mapper.ValidationUtil;
import com.cts.todo.model.Task;
import com.cts.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TaskRepository taskRepository;

    public Task addTask(Task task){
        ValidationUtil.isValidTotalEffortRequired(task);
        com.cts.todo.entity.Task entity = MapperUtil.convertToEntity(task);
        com.cts.todo.entity.Task  insertedTask =  taskRepository.save(entity);
        return MapperUtil.convertToDto(insertedTask);
    }

    public List<Task> addTaskList(List<Task> tasks) {
        ValidationUtil.isValidTotalEffortRequired(tasks);
        List<com.cts.todo.entity.Task> entity = MapperUtil.convertToEntity(tasks);
        List<com.cts.todo.entity.Task>  insertedTasks =  taskRepository.insert(entity);
        return MapperUtil.convertToDto(insertedTasks);
    }

    public List<Task> getAllTasks(){
        List<com.cts.todo.entity.Task> insertedTask =  taskRepository.findAll();
        return MapperUtil.convertToDto(insertedTask);
    }

    public List<Task> getTaskByName(String taskName){
        List<com.cts.todo.entity.Task> tasks =  taskRepository.getTaskByTaskName(taskName);
        return MapperUtil.convertToDto(tasks);
    }

    public Task updateTaskStatus(String taskName ,String taskStatus) throws TaskUpdateFailedException{
        Boolean result =  taskRepository.updateTaskStatus(taskName, taskStatus);
        if(result)
            return getTaskByName(taskName).get(0);
        throw new TaskUpdateFailedException("Task not found");
    }

    public Boolean updateTaskStatus(String taskStatus) throws TaskUpdateFailedException{
        return taskRepository.updateTaskStatus( taskStatus);
    }
}
