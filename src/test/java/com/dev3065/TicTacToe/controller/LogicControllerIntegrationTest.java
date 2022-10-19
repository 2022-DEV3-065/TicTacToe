package com.dev3065.TicTacToe.controller;

import com.dev3065.TicTacToe.domain.IncomingJSON;
import com.dev3065.TicTacToe.domain.Player;
import com.dev3065.TicTacToe.domain.ResponseJSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.inject.Inject;
import java.nio.charset.Charset;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.dev3065.TicTacToe.domain.Player.*;

@WebMvcTest(LogicController.class)
@ExtendWith(SpringExtension.class)
@Import(LogicController.class)
@EnableWebMvc
class LogicControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Inject
    private ObjectMapper objectMapper;

    @Test
    void receivesBoardState() throws Exception {
        IncomingJSON incomingJson = new IncomingJSON(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, X);

        MvcResult result = mockMvc
                .perform(post("/logic")
                        .contentType(APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(incomingJson)))
                .andReturn();

        ResponseJSON expectedResponse = new ResponseJSON(List.of(X, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), "NONE");

        assertEquals(objectMapper.writeValueAsString(expectedResponse), result.getResponse().getContentAsString());
    }

    @Test
    void handleClickOnAnyOtherSquareThanTheFirst() throws Exception {
        IncomingJSON incomingJson = new IncomingJSON(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 1, X);

        MvcResult result = mockMvc
                .perform(post("/logic")
                        .contentType(APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(incomingJson)))
                .andReturn();

        ResponseJSON expectedResponse = new ResponseJSON(List.of(EMPTY, X, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY),"NONE");

        assertEquals(objectMapper.writeValueAsString(expectedResponse), result.getResponse().getContentAsString());
    }

    @Test
    void updateSquareAccordingToTurn() throws Exception {
        IncomingJSON incomingJson = new IncomingJSON(List.of(EMPTY, X, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, O);

        MvcResult result = mockMvc
                .perform(post("/logic")
                        .contentType(APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(incomingJson)))
                .andReturn();

        ResponseJSON expectedResponse = new ResponseJSON(List.of(O, X, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), "NONE");

        assertEquals(objectMapper.writeValueAsString(expectedResponse), result.getResponse().getContentAsString());
    }


    @Test
    void throwErrorWhenPlayerPlaysOnOccupiedSquare() throws Exception {
        IncomingJSON incomingJson = new IncomingJSON(List.of(EMPTY, X, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 1, O);

        mockMvc
                .perform(post("/logic")
                        .contentType(APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(incomingJson)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void winnerPresent_InARow() throws Exception {
        IncomingJSON incomingJson = new IncomingJSON(List.of(EMPTY, X, X, EMPTY, O, O, EMPTY, EMPTY, EMPTY), 0, X);

        MvcResult result = mockMvc
                .perform(post("/logic")
                        .contentType(APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(incomingJson)))
                .andReturn();

        ResponseJSON expectedResponse = new ResponseJSON(List.of(X, X, X, EMPTY, O, O, EMPTY, EMPTY, EMPTY), "X");
        assertEquals(objectMapper.writeValueAsString(expectedResponse), result.getResponse().getContentAsString());

    }

    @Test
    void winnerPresent_InAColumn() throws Exception {
        IncomingJSON incomingJson = new IncomingJSON(List.of(EMPTY, X, EMPTY, X, EMPTY, O, EMPTY, X, O), 2,   O);

        MvcResult result = mockMvc
                .perform(post("/logic")
                        .contentType(APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(incomingJson)))
                .andReturn();

        ResponseJSON expectedResponse = new ResponseJSON(List.of(EMPTY, X, O, X, EMPTY, O, EMPTY, X, O), "O");
        assertEquals(objectMapper.writeValueAsString(expectedResponse), result.getResponse().getContentAsString());

    }

    @Test
    void winnerPresent_InADiagonal() throws Exception {
        IncomingJSON incomingJson = new IncomingJSON(List.of(X, X, EMPTY, EMPTY, X, O, O, O, EMPTY), 8, X);

        MvcResult result = mockMvc
                .perform(post("/logic")
                        .contentType(APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(incomingJson)))
                .andReturn();

        ResponseJSON expectedResponse = new ResponseJSON(List.of(X, X, EMPTY, EMPTY, X, O, O, O, X), "X");
        assertEquals(objectMapper.writeValueAsString(expectedResponse), result.getResponse().getContentAsString());

    }

    @Test
    void draw() throws Exception {
        IncomingJSON incomingJson = new IncomingJSON(List.of(X, O, X, O, X, X, O, EMPTY, O), 7, X);

        MvcResult result = mockMvc
                .perform(post("/logic")
                        .contentType(APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(incomingJson)))
                .andReturn();

        ResponseJSON expectedResponse = new ResponseJSON(List.of(X, O, X, O, X, X, O, X, O), "DRAW");
        assertEquals(objectMapper.writeValueAsString(expectedResponse), result.getResponse().getContentAsString());

    }

}