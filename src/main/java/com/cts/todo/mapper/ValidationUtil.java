package com.cts.todo.mapper;

import com.cts.todo.exception.InvalidTaskException;
import com.cts.todo.model.Task;

import java.util.List;

public class ValidationUtil {


    public static void isValidTotalEffortRequired(List<Task> taskList) {
        if(!taskList.stream().allMatch(task -> Integer.valueOf(task.getTotalEffortRequired()) > 0)){
            throw new InvalidTaskException("Total effort required should be greater than 0");
        }
    }

    public static void isValidTotalEffortRequired(Task task) {
        if(! (task != null && Integer.valueOf(task.getTotalEffortRequired()) > 0 ? true : false)){
            throw new InvalidTaskException("Total effort required should be greater than 0");
        }
    }
}
