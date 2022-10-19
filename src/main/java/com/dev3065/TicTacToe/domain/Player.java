package com.dev3065.TicTacToe.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum Player {

    X("X"),
    O("O"),
    EMPTY("-");

    private String value;

    private Player(String value)
    {
        this.value = value;
    }

    public String toString()
    {
        return this.value;
    }

    private static Map<String, Player> namesMap = new HashMap<String, Player>(3);

    static {
        namesMap.put("X", X);
        namesMap.put("O", O);
        namesMap.put("-", EMPTY);
    }

    //for deserialization
    @JsonCreator
    public static Player forValue(String value) {
        return namesMap.get(value);
    }

    //for serialization
    @JsonValue
    public String toValue() {
        for (Map.Entry<String, Player> entry : namesMap.entrySet()) {
            if (entry.getValue() == this)
                return entry.getKey();
        }
        return null; // or fail
    }
}
