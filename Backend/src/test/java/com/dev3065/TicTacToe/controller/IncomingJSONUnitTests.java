package com.dev3065.TicTacToe.controller;

import com.dev3065.TicTacToe.domain.IncomingJSON;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.dev3065.TicTacToe.domain.Player.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
