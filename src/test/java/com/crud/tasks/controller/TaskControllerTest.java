package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    void shouldGetTasks() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "title", "text");
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(taskDto);

        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(taskDtoList);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/tasks"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void shouldGetTask() throws Exception {
        //Given
        TaskDto taskDto = new TaskDto(1L, "Test", "Test content");
        Task task = new Task(1L, "Test", "Test content");

        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(dbService.getTask(1L)).thenReturn(Optional.of(task));

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/tasks/1")
                        .param("taskId", String.valueOf(1)))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        Task task = new Task(1L, "Test", "Test content");

        when(dbService.getTask(1L)).thenReturn(Optional.of(task));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/1")
                        .param("taskId", String.valueOf(1)))
                .andExpect(status().is(200));
    }

    @Test
    void shouldUpdateTask() throws Exception {
        Task task = new Task(1L, "title", "text");
        TaskDto taskDto = new TaskDto(1L, "title", "text");
        when(taskMapper.mapToTask(taskDto)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(dbService.saveTask(task)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldCreateTask() throws Exception {
        // Given
        Task task = new Task(1L, "title", "text");
        TaskDto taskDto = new TaskDto(1L, "title", "text");

        when(taskMapper.mapToTask(taskDto)).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);


        // When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());

        verify(dbService, times(1)).saveTask(task);
    }
}