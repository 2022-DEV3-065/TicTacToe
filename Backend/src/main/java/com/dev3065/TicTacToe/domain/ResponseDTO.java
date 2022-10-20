package com.dev3065.TicTacToe.domain;

import java.util.List;

public class ResponseDTO {

    private List<Player> state;
    private String winner;

    public ResponseDTO() {
    }

    public ResponseDTO(List<Player> state, String winner) {
        this.state = state;
        this.winner = winner;
    }

    public List<Player> getState() {
        return state;
    }

    public void setState(List<Player> state) {
        this.state = state;
    }

    public void setSquareClicked(Integer squareClicked, Player turn) {
        state.set(squareClicked, turn);
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
