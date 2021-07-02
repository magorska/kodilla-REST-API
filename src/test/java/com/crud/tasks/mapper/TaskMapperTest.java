package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Component
class TaskMapperTest {

    @Test
    void testMapToTask() {
        //Given
        TaskMapper taskMapper = new TaskMapper();
        TaskDto task = new TaskDto(1L, "title", "text" );
        //When
        Task mappedTask = taskMapper.mapToTask(task);
        //Then
        assertEquals(1L, mappedTask.getId());
        assertEquals("title", mappedTask.getTitle());
        assertEquals("text", mappedTask.getContent());
    }
    @Test
    void testMapToTaskDto() {
        //Given
        TaskMapper taskMapper = new TaskMapper();
        Task task = new Task(1L, "title", "text");
        //When
        TaskDto mappedTaskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(1L, mappedTaskDto.getId());
        assertEquals("title", mappedTaskDto.getTitle());
        assertEquals("text", mappedTaskDto.getContent());
    }
    @Test
    void testMapToTaskDtoList() {
        //Given
        TaskMapper taskMapper = new TaskMapper();
        Task task = new Task(1L, "title", "text");
        List<Task> taskList = Arrays.asList(task);
        //When
        List<TaskDto> mappedTaskList = taskMapper.mapToTaskDtoList(taskList);
        //Then
        assertEquals(1, mappedTaskList.size());
        assertEquals(1L, mappedTaskList.get(0).getId());
        assertEquals("title", mappedTaskList.get(0).getTitle());
        assertEquals("text", mappedTaskList.get(0).getContent());
    }

}