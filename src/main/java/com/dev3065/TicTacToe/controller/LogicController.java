package com.dev3065.TicTacToe.controller;

import com.dev3065.TicTacToe.domain.IncomingJSON;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LogicController {

    @PostMapping("/logic")
    public ResponseEntity<IncomingJSON> logic(@RequestBody IncomingJSON body) {
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
