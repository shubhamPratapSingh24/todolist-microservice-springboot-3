package com.cts.todo.service;


import com.cts.todo.exception.TaskUpdateFailedException;
import com.cts.todo.model.Task;
import com.cts.todo.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

    @ExtendWith(MockitoExtension.class)
    public class TodoServiceImplTest {

        @Mock
        private TaskRepository taskRepository;

        @InjectMocks
        private TodoServiceImpl todoService;

        private Task task;
        private com.cts.todo.entity.Task entityTask;

        @BeforeEach
        void setUp() {
            task = new Task("Task1", "Description1", LocalDateTime.now(), LocalDateTime.now().plusDays(1), "Pending", "5");
            entityTask = new com.cts.todo.entity.Task();
            // Set entityTask fields accordingly
        }

        @Test
        void testAddTask() {
            when(taskRepository.save(any(com.cts.todo.entity.Task.class))).thenReturn(entityTask);
            Task result = todoService.addTask(task);
            assertNotNull(result);
            verify(taskRepository, times(1)).save(any(com.cts.todo.entity.Task.class));
        }

        @Test
        void testAddTaskList() {
            List<Task> tasks = Arrays.asList(task);
            List<com.cts.todo.entity.Task> entityTasks = Arrays.asList(entityTask);
            when(taskRepository.insert(anyList())).thenReturn(entityTasks);
            List<Task> result = todoService.addTaskList(tasks);
            assertNotNull(result);
            assertEquals(1, result.size());
            verify(taskRepository, times(1)).insert(anyList());
        }

        @Test
        void testGetAllTasks() {
            List<com.cts.todo.entity.Task> entityTasks = Arrays.asList(entityTask);
            when(taskRepository.findAll()).thenReturn(entityTasks);
            List<Task> result = todoService.getAllTasks();
            assertNotNull(result);
            assertEquals(1, result.size());
            verify(taskRepository, times(1)).findAll();
        }

        @Test
        void testGetTaskByName() {
            List<com.cts.todo.entity.Task> entityTasks = Arrays.asList(entityTask);
            when(taskRepository.getTaskByTaskName(anyString())).thenReturn(entityTasks);
            List<Task> result = todoService.getTaskByName("Task1");
            assertNotNull(result);
            assertEquals(1, result.size());
            verify(taskRepository, times(1)).getTaskByTaskName(anyString());
        }

        @Test
        void testUpdateTaskStatus() throws TaskUpdateFailedException {
            when(taskRepository.updateTaskStatus(anyString(), anyString())).thenReturn(true);
            when(taskRepository.getTaskByTaskName(anyString())).thenReturn(Arrays.asList(entityTask));
            Task result = todoService.updateTaskStatus("Task1", "Completed");
            assertNotNull(result);
            verify(taskRepository, times(1)).updateTaskStatus(anyString(), anyString());
        }

        @Test
        void testUpdateTaskStatusThrowsException() {
            when(taskRepository.updateTaskStatus(anyString(), anyString())).thenReturn(false);
            assertThrows(TaskUpdateFailedException.class, () -> {
                todoService.updateTaskStatus("Task1", "Completed");
            });
        }
    }


