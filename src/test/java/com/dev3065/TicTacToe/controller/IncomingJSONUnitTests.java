package com.dev3065.TicTacToe.controller;

import com.dev3065.TicTacToe.domain.IncomingJSON;
import com.dev3065.TicTacToe.domain.ResponseJSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.inject.Inject;
import java.util.List;

import static com.dev3065.TicTacToe.domain.Player.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
public class IncomingJSONUnitTests {

    @Test
    void checkIfBoardLengthIsValid() {
        IncomingJSON correctLengthState = new IncomingJSON(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, X);
        assertTrue(correctLengthState.isBoardLengthNine());

        IncomingJSON wrongLengthState = new IncomingJSON(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, X);
        assertFalse(wrongLengthState.isBoardLengthNine());

        IncomingJSON emptyState = new IncomingJSON(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, X);
        assertFalse(emptyState.isBoardLengthNine());
    }

    @Test
    void checkIfSquareClickedValueIsValid() {
        IncomingJSON correctSquareClicked = new IncomingJSON(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, X);
        assertTrue(correctSquareClicked.isSqaureClickedValueValid());

        IncomingJSON wrongSquareClicked = new IncomingJSON(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 10, X);
        assertFalse(wrongSquareClicked.isSqaureClickedValueValid());

        IncomingJSON emptySquareClicked = new IncomingJSON(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), null, X);
        assertFalse(emptySquareClicked.isSqaureClickedValueValid());
    }

    @Test
    void checkIfPlayerIsValid() {
        IncomingJSON correctPlayerX = new IncomingJSON(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, X);
        assertTrue(correctPlayerX.isValidPlayer());

        IncomingJSON correctPlayerO = new IncomingJSON(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, O);
        assertTrue(correctPlayerO.isValidPlayer());

        IncomingJSON wrongPlayer = new IncomingJSON(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, EMPTY);
        assertFalse(wrongPlayer.isValidPlayer());

        IncomingJSON emptyPlayer = new IncomingJSON(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, null);
        assertFalse(emptyPlayer.isValidPlayer());
    }

    @Test
    void checkIfClickedSquareIsAvaialble() {
        IncomingJSON emptySquare = new IncomingJSON(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, X);
        assertTrue(emptySquare.isClickedSquareEmpty());

        IncomingJSON notEmptySquare = new IncomingJSON(List.of(X, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, X);
        assertFalse(notEmptySquare.isClickedSquareEmpty());
    }

}
