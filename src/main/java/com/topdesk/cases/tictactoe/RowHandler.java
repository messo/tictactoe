package com.topdesk.cases.tictactoe;

import com.topdesk.cases.tictactoe.impl.Board;

import java.util.List;

/**
 * A handler for a given row (horizontal, vertical or diagonal).
 */
public interface RowHandler {

    /**
     * The handle method.
     *
     * @param board the current board
     * @param row   the given row which should be processed
     * @return {@code true} if there is no need to invoke this handler for further rows, {@code false} otherwise.
     */
    boolean onRow(Board board, List<Field> row);

}
