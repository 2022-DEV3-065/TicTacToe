package com.dev3065.TicTacToe.controller;

import com.dev3065.TicTacToe.domain.IncomingJSON;
import com.dev3065.TicTacToe.domain.ResponseJSON;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.dev3065.TicTacToe.Helper.drawCondition;
import static com.dev3065.TicTacToe.Helper.winCondition;

@RestController
public class LogicController {

    @PostMapping("/logic")
    public ResponseEntity<ResponseJSON> handler(@RequestBody IncomingJSON incomingJSON) {

        if (incomingJSON.isValid()) {
            ResponseJSON responseJSON = new ResponseJSON(incomingJSON.getState(), "NONE");
            responseJSON.setSquareClicked(incomingJSON.getSquareClicked(), incomingJSON.getTurn());

            if (winCondition(responseJSON.getState())) {
                responseJSON.setWinner(incomingJSON.getTurn().toString());
            } else if (drawCondition(responseJSON.getState())) {
                responseJSON.setWinner("DRAW");
            }

            return new ResponseEntity<>(responseJSON, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
