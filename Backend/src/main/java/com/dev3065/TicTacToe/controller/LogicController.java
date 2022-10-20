package com.dev3065.TicTacToe.controller;

import com.dev3065.TicTacToe.domain.IncomingDTO;
import com.dev3065.TicTacToe.domain.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.dev3065.TicTacToe.helpers.WinOrDrawConditionChecker.isADraw;
import static com.dev3065.TicTacToe.helpers.WinOrDrawConditionChecker.isAWin;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class LogicController {

    @PostMapping("/logic")
    public ResponseEntity<ResponseDTO> handler(@RequestBody IncomingDTO incomingJSON) {

        if (incomingJSON.isValid()) {
            ResponseDTO responseDTO = new ResponseDTO(incomingJSON.getState(), "NONE");
            responseDTO.setSquareClicked(incomingJSON.getSquareClicked(), incomingJSON.getTurn());

            if (isAWin(responseDTO.getState())) {
                responseDTO.setWinner(incomingJSON.getTurn().toString());
            } else if (isADraw(responseDTO.getState())) {
                responseDTO.setWinner("DRAW");
            }

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
