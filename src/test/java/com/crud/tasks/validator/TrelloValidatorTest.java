package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TrelloValidatorTest {

    @Test
    void testValidateTrelloBoards() {
        //Given
        TrelloValidator trelloValidator = new TrelloValidator();

        TrelloList trelloList = new TrelloList("#01", "board", false);
        TrelloList trelloList2 = new TrelloList("#02", "board2", false);
        List<TrelloList> trelloListList = Arrays.asList(trelloList, trelloList2);

        TrelloBoard trelloBoard = new TrelloBoard("#1", "test", trelloListList);
        TrelloBoard trelloBoard2 = new TrelloBoard("#2", "board", trelloListList);
        List<TrelloBoard> trelloBoardList = Arrays.asList(trelloBoard, trelloBoard2);

        //When
        List<TrelloBoard> testTrelloList = trelloValidator.validateTrelloBoards(trelloBoardList);

        //Then
        assertEquals(1, testTrelloList.size());
    }

}