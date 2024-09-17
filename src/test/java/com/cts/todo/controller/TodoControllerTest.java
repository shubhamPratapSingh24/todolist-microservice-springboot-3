package com.cts.todo.controller;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cts.todo.controller.TodoController;
import com.cts.todo.model.Task;
import com.cts.todo.service.TodoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoServiceImpl service;

    @Autowired
    private ObjectMapper objectMapper;

    Task task, task2;

    List<Task> taskList;

    @BeforeEach
    public void setUp() {
        task = new Task("Dev", "devlopmentWork",  LocalDateTime.now(), LocalDateTime.now(),"Completed", "2");;
        task2 = new Task("Test", "TestingWork",  LocalDateTime.now(), LocalDateTime.now(),"Pending", "1");
        taskList = Arrays.asList(task, task2);
    }


    @Test
    public void testAddTask() throws Exception {
        when(service.addTask(any(Task.class))).thenReturn(task);
        mockMvc.perform(put("/api/v1/user/addTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskName").value("Dev"))
                .andExpect(jsonPath("$.taskStatus").value("Completed"));
    }

    @Test
    public void testAddTaskList() throws Exception {
        List<Task> taskList = Arrays.asList(task, task2);
        when(service.addTaskList(anyList())).thenReturn(taskList);

        mockMvc.perform(put("/api/v1/user/add-list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskList)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].taskName").value("Dev"))
                .andExpect(jsonPath("$[0].taskStatus").value("Completed"))
                .andExpect(jsonPath("$[1].taskName").value("Test"))
                .andExpect(jsonPath("$[1].taskStatus").value("Pending"));
    }

    @Test
    public void testGetAllTasks() throws Exception {
        when(service.getAllTasks()).thenReturn(taskList);

        mockMvc.perform(get("/api/v1/user/list/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].taskName").value("Dev"))
                .andExpect(jsonPath("$[0].taskStatus").value("Completed"))
                .andExpect(jsonPath("$[1].taskName").value("Test"))
                .andExpect(jsonPath("$[1].taskStatus").value("Pending"));
    }

    @Test
    public void testGetTaskByName() throws Exception {
        when(service.getTaskByName("Dev")).thenReturn(taskList);

        mockMvc.perform(get("/api/v1/user/list/Dev"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].taskName").value("Dev"))
                .andExpect(jsonPath("$[0].taskStatus").value("Completed"));
    }

    @Test
    public void testUpdateTaskStatus() throws Exception {

        when(service.updateTaskStatus("Dev", "Inprogress")).thenReturn(task);

        mockMvc.perform(post("/api/v1/user/update/Dev/Inprogress"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskName").value("Dev"))
                .andExpect(jsonPath("$.taskStatus").value("Completed"));
    }

    @Test
    public void testUpdateTaskStatusByStatus() throws Exception {
        when(service.updateTaskStatus("Completed")).thenReturn(true);

        mockMvc.perform(post("/api/v1/user/update/Completed"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
}
