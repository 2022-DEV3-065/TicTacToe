package com.dev3065.TicTacToe;

import com.dev3065.TicTacToe.domain.Player;
import static com.dev3065.TicTacToe.domain.Player.*;

import java.util.List;

public class Helper {

    public static boolean drawCondition(List<Player> state) {
        for (Player s : state) {
            if (s.equals(EMPTY)) {
                return false;
            }
        }
        return true;
    }

    public static boolean winCondition(List<Player> state) {
        state = state.subList(0, 9);
        if (state.get(0).equals(state.get(1)) && state.get(1).equals(state.get(2)) && !state.get(0).equals(EMPTY)) {
            return true;
        } else if (state.get(3).equals(state.get(4)) && state.get(4).equals(state.get(5)) && !state.get(3).equals(EMPTY)) {
            return true;
        } else if (state.get(6).equals(state.get(7)) && state.get(7).equals(state.get(8)) && !state.get(6).equals(EMPTY)) {
            return true;
        } else if (state.get(0).equals(state.get(3)) && state.get(3).equals(state.get(6)) && !state.get(0).equals(EMPTY)) {
            return true;
        } else if (state.get(1).equals(state.get(4)) && state.get(4).equals(state.get(7)) && !state.get(1).equals(EMPTY)) {
            return true;
        } else if (state.get(2).equals(state.get(5)) && state.get(5).equals(state.get(8)) && !state.get(2).equals(EMPTY)) {
            return true;
        } else if (state.get(0).equals(state.get(4)) && state.get(4).equals(state.get(8)) && !state.get(0).equals(EMPTY)) {
            return true;
        } else if (state.get(2).equals(state.get(4)) && state.get(4).equals(state.get(6)) && !state.get(2).equals(EMPTY)) {
            return true;
        } else {
            return false;
        }
    }
}
