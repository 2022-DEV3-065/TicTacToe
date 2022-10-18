package com.dev3065.TicTacToe.domain;

import java.util.List;

public class IncomingJSON {

    private List<String> state;
    private Integer squareClicked;

    public IncomingJSON() {
    }

    public IncomingJSON(List<String> state, Integer squareClicked) {
        this.state = state;
        this.squareClicked = squareClicked;
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
}
