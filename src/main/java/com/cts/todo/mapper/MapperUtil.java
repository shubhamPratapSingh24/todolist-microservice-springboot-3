package com.cts.todo.mapper;


import com.cts.todo.entity.Task;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class MapperUtil {

    static ModelMapper modelMapper = new ModelMapper();

    public static List<Task> convertToEntity(List<com.cts.todo.model.Task> dtos){
        List<com.cts.todo.entity.Task> tasks = new ArrayList<>();
        for(com.cts.todo.model.Task task: dtos){
            tasks.add(modelMapper.map(task, Task.class));
        }
        return tasks;
    }

    public static Task convertToEntity(com.cts.todo.model.Task dto){
        return modelMapper.map(dto, Task.class);
    }

    public static com.cts.todo.model.Task convertToDto(com.cts.todo.entity.Task entity){
        return modelMapper.map(entity, com.cts.todo.model.Task.class);
    }

    public static List<com.cts.todo.model.Task> convertToDto(List<Task> entity){
        List<com.cts.todo.model.Task> tasks = new ArrayList<>();
        for(Task task: entity){
            tasks.add(modelMapper.map(task, com.cts.todo.model.Task.class));
        }
        return tasks;
    }
}
