package com.dev3065.TicTacToe.controller;

import com.dev3065.TicTacToe.domain.Player;
import com.dev3065.TicTacToe.helpers.WinOrDrawConditionChecker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import static com.dev3065.TicTacToe.domain.Player.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class WinOrDrawConditionCheckerUntiTests {

    @Test
    void isTopRowWinningCondition() {
        List<Player> state = List.of(
                X,
                X,
                X,
                EMPTY,
                EMPTY,
                EMPTY,
                EMPTY,
                EMPTY,
                EMPTY
        );

        assertTrue(WinOrDrawConditionChecker.isAWin(state));
    }

    @Test
    void isMiddleRowWinningCondition() {
        List<Player> state = List.of(
                EMPTY,
                EMPTY,
                EMPTY,
                X,
                X,
                X,
                EMPTY,
                EMPTY,
                EMPTY
        );

        assertTrue(WinOrDrawConditionChecker.isAWin(state));
    }

    @Test
    void isBottomRowWinningCondition() {
        List<Player> state = List.of(
                EMPTY,
                EMPTY,
                EMPTY,
                EMPTY,
                EMPTY,
                EMPTY,
                X,
                X,
                X
        );

        assertTrue(WinOrDrawConditionChecker.isAWin(state));
    }

    @Test
    void isLeftColumnWinningCondition() {
        List<Player> state = List.of(
                X,
                EMPTY,
                EMPTY,
                X,
                EMPTY,
                EMPTY,
                X,
                EMPTY,
                EMPTY
        );

        assertTrue(WinOrDrawConditionChecker.isAWin(state));
    }

    @Test
    void isMiddleColumnWinningCondition() {
        List<Player> state = List.of(
                EMPTY,
                X,
                EMPTY,
                EMPTY,
                X,
                EMPTY,
                EMPTY,
                X,
                EMPTY
        );

        assertTrue(WinOrDrawConditionChecker.isAWin(state));
    }

    @Test
    void isRightColumnWinningCondition() {
        List<Player> state = List.of(
                EMPTY,
                EMPTY,
                X,
                EMPTY,
                EMPTY,
                X,
                EMPTY,
                EMPTY,
                X
        );

        assertTrue(WinOrDrawConditionChecker.isAWin(state));
    }

    @Test
    void isAscendingDiagonalWinningCondition() {
        List<Player> state = List.of(
                X,
                EMPTY,
                EMPTY,
                EMPTY,
                X,
                EMPTY,
                EMPTY,
                EMPTY,
                X
        );

        assertTrue(WinOrDrawConditionChecker.isAWin(state));
    }

    @Test
    void isDescendingDiagonalWinningCondition() {
        List<Player> state = List.of(
                EMPTY,
                EMPTY,
                X,
                EMPTY,
                X,
                EMPTY,
                X,
                EMPTY,
                EMPTY
        );

        assertTrue(WinOrDrawConditionChecker.isAWin(state));
    }



}
