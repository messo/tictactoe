package com.topdesk.cases.tictactoe.impl;

import com.topdesk.cases.tictactoe.Field;
import com.topdesk.cases.tictactoe.RowHandler;
import com.topdesk.cases.tictactoe.FieldStatus;

import java.util.List;

/**
 * A {@link RowHandler} implementation which is looking for a row where there are two pieces of a given type and an empty field.
 * This is used to check if it is possible to win the game (or stopping the opponent from winning).
 */
public class TwoInARow implements RowHandler {

    private final FieldStatus statusToSearch;
    private Field lastEmpty;

    /**
     * Constructs this handler with a given {@link FieldStatus} to search for in the row
     *
     * @param statusToSearch the type of piece we are looking for in a row
     */
    public TwoInARow(FieldStatus statusToSearch) {
        this.statusToSearch = statusToSearch;
    }


    @Override
    public boolean onRow(Board board, List<Field> row) {
        int ok = 0;
        int empty = 0;
        lastEmpty = null;
        for (Field field : row) {
            if (board.fieldHasStatus(field, statusToSearch)) {
                ok++;
            } else if (board.isFieldEmpty(field)) {
                empty++;
                lastEmpty = field;
            }
        }

        if (ok == 2 && empty == 1) {
            return true;
        }

        lastEmpty = null;
        return false;
    }

    /**
     * Upon a successful match on a row, the empty field can be retrieved.
     *
     * @return the empty field in a row where there are two pieces of the given type, {@code null} if
     * there was no match on the previous invocation of {@link #onRow(Board, List)}
     */
    public Field getEmptyField() {
        return lastEmpty;
    }
}
