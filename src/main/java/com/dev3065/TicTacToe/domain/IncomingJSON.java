package com.dev3065.TicTacToe.domain;

import java.util.List;

public class IncomingJSON {

    private List<Player> state;
    private Integer squareClicked;
    private Player turn;

    public IncomingJSON() {
    }

    public IncomingJSON(List<Player> state, Integer squareClicked, Player turn) {
        this.state = state;
        this.squareClicked = squareClicked;
        this.turn = turn;
    }

    public List<Player> getState() {
        return state;
    }

    public Integer getSquareClicked() {
        return squareClicked;
    }

    public void setState(List<Player> state) {
        this.state = state;
    }

    public Player getTurn() {
        return turn;
    }
}
