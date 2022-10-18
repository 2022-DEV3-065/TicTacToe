package com.dev3065.TicTacToe.domain;

import java.util.List;

public class IncomingJSON {

    private List<String> state;
    private Integer squareClicked;
    private String turn;

    public IncomingJSON() {
    }

    public IncomingJSON(List<String> state, Integer squareClicked, String turn) {
        this.state = state;
        this.squareClicked = squareClicked;
        this.turn = turn;
    }

    public List<String> getState() {
        return state;
    }

    public Integer getSquareClicked() {
        return squareClicked;
    }

    public void setState(List<String> state) {
        this.state = state;
    }

    public String getTurn() {
        return turn;
    }
}
