package com.dev3065.TicTacToe.domain;

import java.util.List;

public class ResponseJSON {

    private List<String> state;
    private String winner;

    public ResponseJSON() {
    }

    public ResponseJSON(List<String> state, String winner) {
        this.state = state;
        this.winner = winner;
    }

    public List<String> getState() {
        return state;
    }

    public void setState(List<String> state) {
        this.state = state;
    }

    public void setSquareClicked(Integer squareClicked, String turn) {
        state.set(squareClicked, turn);
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
