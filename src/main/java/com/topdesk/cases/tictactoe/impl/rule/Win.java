package com.topdesk.cases.tictactoe.impl.rule;

import com.topdesk.cases.tictactoe.Field;
import com.topdesk.cases.tictactoe.FieldStatus;
import com.topdesk.cases.tictactoe.impl.Board;
import com.topdesk.cases.tictactoe.impl.TwoInARow;

/**
 * <p>The <b>Win</b> rule.</p>
 * <p><i>If</i> there is a row, column, or diagonal with two of my pieces and a blank space,
 * <i>Then</i> play the blank space (thus winning the game)</p>
 */
public class Win extends AbstractRule {

    @Override
    public Field moveIfAvailable(Board board) {
        return getWinningField(board, board.getNextStatus());
    }

    /**
     * Returns a field which is located in a row where already two fields have the given {@link FieldStatus}.
     *
     * @param board  the board
     * @param status the status we are looking for
     * @return a matching field, or {@code null} if there isn't any field like this
     */
    protected Field getWinningField(Board board, FieldStatus status) {
        // we are looking for fields where only 1 is missing from our color (X/O)
        TwoInARow twoInARow = new TwoInARow(status);
        if (board.iterateOverRows(twoInARow)) {
            return twoInARow.getEmptyField();
        } else {
            return null;
        }
    }

}
