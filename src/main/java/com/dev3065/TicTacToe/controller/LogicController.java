package com.dev3065.TicTacToe.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class LogicController {

    @PostMapping("/hello123")
    public String hello(@RequestBody Object body) {
        return String.format("Hello World!");
    }

    @GetMapping("/hello1234")
    public String hello2() {
        return String.format("Hello World!");
    }
}
