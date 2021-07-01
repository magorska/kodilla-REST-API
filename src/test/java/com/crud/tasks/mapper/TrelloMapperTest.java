package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class TrelloMapperTest {

    @Test
    void testMapToBoards() {
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();

        TrelloListDto trelloListDto = new TrelloListDto("#01", "testTrelloList", false);
        List<TrelloListDto> trelloListDtoList = Arrays.asList(trelloListDto);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("#1", "testList", trelloListDtoList );
        List<TrelloBoardDto> trelloBoardDtoList = Arrays.asList(trelloBoardDto);

        //When
        List<TrelloBoard> mappedTrelloBoard = trelloMapper.mapToBoards(trelloBoardDtoList);

        //Then
        assertEquals(1, mappedTrelloBoard.size());
        assertEquals(trelloBoardDtoList.get(0).getId(), mappedTrelloBoard.get(0).getId());
        assertEquals(trelloBoardDtoList.get(0).getName(), mappedTrelloBoard.get(0).getName());
    }

    @Test
    void testMapToBoardsDto() {
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();

        TrelloList trelloList = new TrelloList("#01", "testTrelloList", false);
        List<TrelloList> trelloListList = Arrays.asList(trelloList);

        TrelloBoard trelloBoard = new TrelloBoard("#1", "testTrelloBoard", trelloListList);
        List<TrelloBoard> trelloBoardList = Arrays.asList(trelloBoard);

        //When
        List<TrelloBoardDto> mappedTrelloBoard = trelloMapper.mapToBoardsDto(trelloBoardList);

        //Then
        assertEquals(1, mappedTrelloBoard.size());
        assertEquals(trelloBoardList.get(0).getId(), mappedTrelloBoard.get(0).getId());
        assertEquals(trelloBoardList.get(0).getName(), mappedTrelloBoard.get(0).getName());
    }

    @Test
    void testMapToList() {

        //Given
        TrelloMapper trelloMapper = new TrelloMapper();

        TrelloListDto trelloListDto = new TrelloListDto("#1", "testList", false);
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        trelloListDtoList.add(trelloListDto);

        //When
        List<TrelloList> mappedTrelloListDto = trelloMapper.mapToList(trelloListDtoList);

        //Then
        assertEquals(1, mappedTrelloListDto.size());
        assertEquals(trelloListDtoList.get(0).getId(), mappedTrelloListDto.get(0).getId());
        assertEquals(trelloListDtoList.get(0).getName(), mappedTrelloListDto.get(0).getName());
    }

    @Test
    void testMapToListDto() {

        //Given
        TrelloMapper trelloMapper = new TrelloMapper();

        TrelloList trelloLists = new TrelloList("#1", "testTrelloList", false);
        List<TrelloList> trelloListList = Arrays.asList(trelloLists);

        //When
        List<TrelloListDto> mappedTrelloList = trelloMapper.mapToListDto(trelloListList);

        //Then
        assertEquals(1, mappedTrelloList.size());
        assertEquals(trelloListList.get(0).getId(), mappedTrelloList.get(0).getId());
        assertEquals(trelloListList.get(0).getName(), mappedTrelloList.get(0).getName());
    }

    @Test
    void testMapToCard() {

        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloCardDto trelloCardDto = new TrelloCardDto("testCard", "it's test card", "pos","listId");

        //When
        TrelloCard mappedTrelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("testCard", mappedTrelloCard.getName());
        assertEquals("it's test card", mappedTrelloCard.getDescription());
        assertEquals("pos", mappedTrelloCard.getPos());
        assertEquals("listId", mappedTrelloCard.getListId());
    }

    @Test
    void testMapToCardDto() {

        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloCard trelloCard = new TrelloCard("testCard", "it's test card", "pos", "listId");

        //When
        TrelloCardDto mappedTrelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("testCard", mappedTrelloCardDto.getName());
        assertEquals("it's test card", mappedTrelloCardDto.getDescription());
        assertEquals("pos", mappedTrelloCardDto.getPos());
        assertEquals("listId", mappedTrelloCardDto.getListId());


    }

}