package com.cts.todo.repository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.cts.todo.entity.Task;
import com.cts.todo.repository.impl.TaskRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import com.mongodb.client.result.UpdateResult;
import java.util.List;
import java.util.Arrays;

public class TaskRepositoryImplTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private TaskRepositoryImpl taskRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTaskByTaskName() {
        String taskName = "Test Task";
        Task task = new Task();
        task.setTaskName(taskName);
        List<Task> expectedTasks = Arrays.asList(task);

        when(mongoTemplate.find(any(Query.class), eq(Task.class), eq("task"))).thenReturn(expectedTasks);

        List<Task> actualTasks = taskRepository.getTaskByTaskName(taskName);

        assertEquals(expectedTasks, actualTasks);
    }

    @Test
    public void testUpdateTaskStatusWithTaskName() {
        String taskName = "Test Task";
        String taskStatus = "Completed";
        UpdateResult updateResult = mock(UpdateResult.class);

        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(mongoTemplate.updateMulti(any(Query.class), any(Update.class), eq(Task.class), eq("task"))).thenReturn(updateResult);

        Boolean result = taskRepository.updateTaskStatus(taskName, taskStatus);

        assertTrue(result);
    }

    @Test
    public void testUpdateTaskStatusWithoutTaskName() {
        String taskStatus = "Completed";
        UpdateResult updateResult = mock(UpdateResult.class);

        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(mongoTemplate.updateMulti(any(Query.class), any(Update.class), eq(Task.class), eq("task"))).thenReturn(updateResult);

        Boolean result = taskRepository.updateTaskStatus(taskStatus);

        assertTrue(result);
    }
}

