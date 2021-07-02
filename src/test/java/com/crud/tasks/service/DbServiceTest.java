package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    void testGetAllTasks() {
        //Given
        Task task = new Task(1L, "title", "text");
        List<Task> taskList = Arrays.asList(task);
        when(repository.findAll()).thenReturn(taskList);

        //When
        List<Task> testTaskList = dbService.getAllTasks();

        //Then
        assertEquals(1,testTaskList.size());
        assertEquals(1L, testTaskList.get(0).getId());
        assertEquals("title", testTaskList.get(0).getTitle());
        assertEquals("text", testTaskList.get(0).getContent());
    }
    @Test
    void testSaveTask() {
        //Given
        Task task = new Task(1L, "title", "text");
        when(repository.save(task)).thenReturn(task);

        //When
        Task savedTask = dbService.saveTask(task);

        //Then
        assertEquals(1L, savedTask.getId());
        assertEquals("title", savedTask.getTitle());
        assertEquals("text", savedTask.getContent());

    }
    @Test
    void testGetTask() {
        // Given
        Task task = new Task(1L, "test_title", "test_content");
        when(repository.findById(1L)).thenReturn(Optional.of(task));

        //When
        Optional<Task> testTask = dbService.getTask(1L);

        //Then
        assertEquals(task, testTask.get());
        assertTrue(testTask.isPresent());
    }
    @Test
    void testDeleteTask() {
        //Given
        Task task = new Task(1L, "test_title", "test_content");

        //When
        dbService.deleteTask(1L);

        //Then
        verify(repository, times(1)).deleteById(1L);
    }
}