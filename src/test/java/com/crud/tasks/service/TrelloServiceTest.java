package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;


    @Test
    void testFetchTrelloBoards() {
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto();
        List<TrelloBoardDto> trelloBoardDtoList = Arrays.asList(trelloBoardDto);
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);

        //When
        List<TrelloBoardDto> testTrelloBoardDto = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(1, testTrelloBoardDto.size());
    }

    @Test
    void testFetchTrelloBoardWhenEmpty() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloService.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(0, trelloBoardDtos.size());
    }

    @Test
    void testCreateTrelloCard() {
        //Given
        Badges badges = new Badges();
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("#1", "testName", "testURL", badges);
        TrelloCardDto trelloCardDto = new TrelloCardDto("testName", "testDesc", "testPos", "testListId");

        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
        when(adminConfig.getAdminMail()).thenReturn("maila@gmail.com");

        //When
        CreatedTrelloCardDto testCreatedTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertEquals("#1", testCreatedTrelloCardDto.getId());
        assertEquals("testName", testCreatedTrelloCardDto.getName());
        assertEquals("testURL", testCreatedTrelloCardDto.getShortUrl());
        assertEquals(badges, testCreatedTrelloCardDto.getBadges());
    }

}