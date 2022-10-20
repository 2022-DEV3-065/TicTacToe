package com.dev3065.TicTacToe.controller;

import com.dev3065.TicTacToe.domain.IncomingDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.dev3065.TicTacToe.domain.Player.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class IncomingDTOUnitTests {

    @Test
    void checkIfBoardLengthIsValid() {
        IncomingDTO correctLengthState = new IncomingDTO(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, X);
        assertTrue(correctLengthState.isBoardLengthNine());

        IncomingDTO wrongLengthState = new IncomingDTO(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, X);
        assertFalse(wrongLengthState.isBoardLengthNine());

        IncomingDTO emptyState = new IncomingDTO(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, X);
        assertFalse(emptyState.isBoardLengthNine());
    }

    @Test
    void checkIfSquareClickedValueIsValid() {
        IncomingDTO correctSquareClicked = new IncomingDTO(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, X);
        assertTrue(correctSquareClicked.isSqaureClickedValueValid());

        IncomingDTO wrongSquareClicked = new IncomingDTO(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 10, X);
        assertFalse(wrongSquareClicked.isSqaureClickedValueValid());

        IncomingDTO emptySquareClicked = new IncomingDTO(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), null, X);
        assertFalse(emptySquareClicked.isSqaureClickedValueValid());
    }

    @Test
    void checkIfPlayerIsValid() {
        IncomingDTO correctPlayerX = new IncomingDTO(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, X);
        assertTrue(correctPlayerX.isValidPlayer());

        IncomingDTO correctPlayerO = new IncomingDTO(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, O);
        assertTrue(correctPlayerO.isValidPlayer());

        IncomingDTO wrongPlayer = new IncomingDTO(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, EMPTY);
        assertFalse(wrongPlayer.isValidPlayer());

        IncomingDTO emptyPlayer = new IncomingDTO(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, null);
        assertFalse(emptyPlayer.isValidPlayer());
    }

    @Test
    void checkIfClickedSquareIsAvaialble() {
        IncomingDTO emptySquare = new IncomingDTO(List.of(EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, X);
        assertTrue(emptySquare.isClickedSquareEmpty());

        IncomingDTO notEmptySquare = new IncomingDTO(List.of(X, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY), 0, X);
        assertFalse(notEmptySquare.isClickedSquareEmpty());
    }

}
