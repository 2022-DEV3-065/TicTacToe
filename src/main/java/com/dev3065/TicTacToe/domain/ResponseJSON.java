package com.dev3065.TicTacToe.domain;

import java.util.List;

public class ResponseJSON {

    private List<String> state;

    public ResponseJSON() {
    }

    public ResponseJSON(List<String> state) {
        this.state = state;
    }

    public List<String> getState() {
        return state;
    }

    public void setState(List<String> state) {
        this.state = state;
    }
}
