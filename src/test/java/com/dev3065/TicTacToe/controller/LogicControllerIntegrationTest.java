package com.dev3065.TicTacToe.controller;

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

import java.nio.charset.Charset;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LogicController.class)
@ExtendWith(SpringExtension.class)
@Import(LogicController.class)
@EnableWebMvc
class LogicControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(APPLICATION_JSON.getType(), APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Test
    void hello() throws Exception {
        RequestBuilder request = get("/hello1234");
        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals("Hello World!", result.getResponse().getContentAsString());
    }

    @Test
    void hello2() throws Exception {
        MvcResult result = mockMvc
                .perform(post("/hello123")
                        .contentType(APPLICATION_JSON)
                        .content("{\"name\":\"John\"}"))
                .andReturn();
        assertEquals("Hello World!", result.getResponse().getContentAsString());
    }


}