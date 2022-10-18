package com.dev3065.TicTacToe.controller;

import com.dev3065.TicTacToe.domain.IncomingJSON;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


@RestController
public class LogicController {

    private static final Logger LOGGER = getLogger(LogicController.class);

    @PostMapping("/logic")
    public ResponseEntity<IncomingJSON> logic(@RequestBody IncomingJSON body) {

        IncomingJSON oneClickResponse = new IncomingJSON();
        oneClickResponse.setState(List.of("X","-","-","-","-","-","-","-","-"));

        return new ResponseEntity<>(oneClickResponse, HttpStatus.OK);
    }
}