package com.topdesk.cases.tictactoe.impl;

import com.topdesk.cases.tictactoe.Field;
import com.topdesk.cases.tictactoe.FieldStatus;
import com.topdesk.cases.tictactoe.RowHandler;

import java.util.LinkedList;
import java.util.List;

/**
 * A {@link RowHandler} implementation which is looking for rows where there are only one piece of a given type and
 * the other two fields are empty. This is used to determine if forking is possible (or stopping the opponent from forking).
 * <p/>
 * Note: This handler never returns with {@code true} in the handle method, since every row must be processed.
 * Also because of this an instance can be only used once for an iteration over the board.
 */
public class OneInARowCollector implements RowHandler {

    private final FieldStatus statusToSearch;
    private List<List<Field>> rowsWithOne = new LinkedList<>();

    /**
     * Constructs this handler with a given {@link FieldStatus} to search for in the row
     *
     * @param statusToSearch the type of piece we are looking for in a row
     */
    public OneInARowCollector(FieldStatus statusToSearch) {
        this.statusToSearch = statusToSearch;
    }


    @Override
    public boolean onRow(Board board, List<Field> row) {
        int ok = 0;
        int empty = 0;
        for (Field field : row) {
            if (board.fieldHasStatus(field, statusToSearch)) {
                ok++;
            } else if (board.isFieldEmpty(field)) {
                empty++;
            }
        }

        if (ok == 1 && empty == 2) {
            rowsWithOne.add(row);
        }

        // we want to process all the rows, so we must return with false all the time
        return false;
    }

    /**
     * Returns the rows with one piece (from the given type) and two empty fields.
     *
     * @return rows
     */
    public List<List<Field>> getRowsWithOne() {
        return rowsWithOne;
    }

}
