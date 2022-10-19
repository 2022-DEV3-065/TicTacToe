package com.dev3065.TicTacToe.domain;

import org.slf4j.Logger;

import java.util.List;

import static com.dev3065.TicTacToe.domain.Player.EMPTY;
import static org.slf4j.LoggerFactory.getLogger;

public class IncomingJSON {

    private static final Logger LOGGER = getLogger(IncomingJSON.class);

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

    public boolean isValid() {
        return isBoardLengthNine() &&
                isSqaureClickedValueValid() &&
                isValidPlayer() &&
                isClickedSquareEmpty();
    }

    public boolean isClickedSquareEmpty() {
        try {
            return state.get(squareClicked) == EMPTY;
        } catch (IndexOutOfBoundsException e) {
            LOGGER.error("Invalid square clicked: " + squareClicked);
            return false;
        }
    }

    public boolean isValidPlayer() {
        if (turn != null && !turn.equals(EMPTY)) {
            return true;
        }
        else {
            LOGGER.error("Invalid player");
            return false;
        }
    }

    public boolean isSqaureClickedValueValid() {
        if (squareClicked!=null && squareClicked >= 0 && squareClicked <= 8){
            return true;
        }
        else {
            LOGGER.error("Invalid value fot the square clicked");
            return false;
        }
    }

    public boolean isBoardLengthNine() {
        if(state != null && state.size() == 9){
            return true;
        }
        else {
            LOGGER.error("Invalid board length");
            return false;
        }
    }
}
