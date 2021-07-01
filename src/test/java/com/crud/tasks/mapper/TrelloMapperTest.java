package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
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
        assertEquals(trelloBoardList.get(0).getId(), mappedTrelloBoard.get(0).getId());
        assertEquals(trelloBoardList.get(0).getName(), mappedTrelloBoard.get(0).getName());
    }

}