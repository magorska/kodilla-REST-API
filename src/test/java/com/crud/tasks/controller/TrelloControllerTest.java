package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.facade.TrelloFacade;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;

@SpringJUnitWebConfig
@WebMvcTest(TrelloController.class)
class TrelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrelloFacade trelloFacade;

    @Test
    void shouldGetTrelloBoards() throws Exception {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("#1", "testName", false);
        List<TrelloListDto> trelloLists = Arrays.asList(trelloListDto);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("#01", "testName", trelloLists);
        List<TrelloBoardDto> trelloBoards = Arrays.asList(trelloBoardDto);

        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        //When&Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/trello/boards"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect( jsonPath("$", hasSize(1)))
                .andExpect( jsonPath("$[0].lists", hasSize(1)));
    }

}