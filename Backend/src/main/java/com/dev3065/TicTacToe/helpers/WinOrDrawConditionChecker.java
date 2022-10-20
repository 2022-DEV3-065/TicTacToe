package com.dev3065.TicTacToe.helpers;

import com.dev3065.TicTacToe.domain.Player;

import java.util.List;
import java.util.stream.Stream;

import static com.dev3065.TicTacToe.domain.Player.EMPTY;

public class WinOrDrawConditionChecker {

    public static boolean isADraw(List<Player> state) {
        return state
            .stream()
            .allMatch(square -> square != EMPTY);
    }

    public static boolean isAWin(List<Player> state) {
        state = state.subList(0, 9);
        return isWinningLine(0, 1, 2, state) ||
                isWinningLine(3, 4, 5, state) ||
                isWinningLine(6, 7, 8, state) ||
                isWinningLine(0, 3, 6, state) ||
                isWinningLine(1, 4, 7, state) ||
                isWinningLine(2, 5, 8, state) ||
                isWinningLine(0, 4, 8, state) ||
                isWinningLine(2, 4, 6, state);
    }

    private static boolean isWinningLine(int i, int i1, int i2, List<Player> state) {
        return state.get(i).equals(state.get(i1)) && state.get(i1).equals(state.get(i2)) && !state.get(i).equals(EMPTY);
    }
}
