package com.dev3065.TicTacToe.controller;

import java.util.List;

public class IncomingJson {
    private List<String> state;

    public IncomingJson(List<String> state) {
        this.state = state;
    }


    public List<String> getState() {
        return state;
    }

    public void setState(List<String> state) {
        this.state = state;
    }
}
