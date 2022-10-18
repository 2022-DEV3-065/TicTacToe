package com.dev3065.TicTacToe.controller;

import com.dev3065.TicTacToe.domain.IncomingJSON;
import com.dev3065.TicTacToe.domain.ResponseJSON;
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
    public ResponseEntity<ResponseJSON> handler(@RequestBody IncomingJSON incomingJSON) {

        if (!incomingJSON.getState().get(incomingJSON.getSquareClicked()).equals("-")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ResponseJSON responseJSON = new ResponseJSON(incomingJSON.getState(), "NONE");
        responseJSON.setSquareClicked(incomingJSON.getSquareClicked(), incomingJSON.getTurn());

        if (winCondition(responseJSON.getState())) {
            responseJSON.setWinner(incomingJSON.getTurn());
        }
        else if (drawCondition(responseJSON.getState())) {
            responseJSON.setWinner("DRAW");
        }

        return new ResponseEntity<>(responseJSON, HttpStatus.OK);
    }

    private boolean drawCondition(List<String> state) {
        for (String s : state) {
            if (s.equals("-")) {
                return false;
            }
        }
        return true;
    }

    private boolean winCondition(List<String> state) {
        state = state.subList(0, 9);
        if (state.get(0).equals(state.get(1)) && state.get(1).equals(state.get(2)) && !state.get(0).equals("-")) {
            return true;
        } else if (state.get(3).equals(state.get(4)) && state.get(4).equals(state.get(5)) && !state.get(3).equals("-")) {
            return true;
        } else if (state.get(6).equals(state.get(7)) && state.get(7).equals(state.get(8)) && !state.get(6).equals("-")) {
            return true;
        } else if (state.get(0).equals(state.get(3)) && state.get(3).equals(state.get(6)) && !state.get(0).equals("-")) {
            return true;
        } else if (state.get(1).equals(state.get(4)) && state.get(4).equals(state.get(7)) && !state.get(1).equals("-")) {
            return true;
        } else if (state.get(2).equals(state.get(5)) && state.get(5).equals(state.get(8)) && !state.get(2).equals("-")) {
            return true;
        } else if (state.get(0).equals(state.get(4)) && state.get(4).equals(state.get(8)) && !state.get(0).equals("-")) {
            return true;
        } else if (state.get(2).equals(state.get(4)) && state.get(4).equals(state.get(6)) && !state.get(2).equals("-")) {
            return true;
        } else {
            return false;
        }
    }
}
