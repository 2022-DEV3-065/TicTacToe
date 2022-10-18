package com.dev3065.TicTacToe.controller;

import com.dev3065.TicTacToe.domain.IncomingJSON;
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
        IncomingJSON incomingJson = new IncomingJSON(List.of("-", "-", "-", "-", "-", "-", "-", "-", "-"));

        MvcResult result = mockMvc
                .perform(post("/logic")
                        .contentType(APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(incomingJson)))
                .andReturn();

        IncomingJSON expectedResponse = new IncomingJSON(List.of("X", "-", "-", "-", "-", "-", "-", "-", "-"));

        assertEquals(objectMapper.writeValueAsString(expectedResponse), result.getResponse().getContentAsString());
    }


}