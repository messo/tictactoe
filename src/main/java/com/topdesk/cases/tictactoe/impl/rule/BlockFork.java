package com.topdesk.cases.tictactoe.impl.rule;

import com.topdesk.cases.tictactoe.Field;
import com.topdesk.cases.tictactoe.impl.Board;
import com.topdesk.cases.tictactoe.impl.OneInARowCollector;
import com.topdesk.cases.tictactoe.FieldStatus;

import java.util.List;

/**
 * <p>The <b>Block Fork</b> rule.</p>
 * <p><i>If</i> there are two intersecting rows, columns, or diagonals with one of my pieces and two blanks, and <br />
 * <i>If</i> the intersecting space is empty, <br />
 * <i>Then</i><br />
 * <i>If</i> there is an empty location that creates a two-in-a-row for me (thus forcing my opponent to block rather than fork),<br />
 * <i>Then</i> move to the location.<br />
 * <i>Else</i> move to the Intersection space (thus occupying the location that my opponent could use to fork).</p>
 */
public class BlockFork extends Fork {

    @Override
    public Field moveIfAvailable(Board board) {
        Field forkingField = getForkingField(board, opposite(board.getNextStatus()));
        if (forkingField == null) {
            return null;
        }

        // first try to make a two-in-a-row, but carefully, not to make a forking position for the opponent.
        Field twoInARowMaker = getTwoInARowMakerField(board);
        if (twoInARowMaker != null) {
            return twoInARowMaker;
        } else {
            return forkingField;
        }
    }

    private Field getTwoInARowMakerField(Board board) {
        FieldStatus nextStatus = board.getNextStatus();
        OneInARowCollector oneInARowCollector = new OneInARowCollector(nextStatus);
        board.iterateOverRows(oneInARowCollector);

        // for every row with one piece of our color, we try to make two-in-a-row, but only if the opponent
        // cannot win by blocking us (so basically making a fork himself)
        for (List<Field> rowWithOne : oneInARowCollector.getRowsWithOne()) {
            Field empty1 = null;
            Field empty2 = null;
            for (Field field : rowWithOne) {
                if (board.isFieldEmpty(field)) {
                    if (empty1 == null) {
                        empty1 = field;
                    } else {
                        empty2 = field;
                    }
                }
            }

            // try to put into empty1, then test if the opponent cannot fork in empty2
            Board board1 = board.withOverriddenPosition(empty1, nextStatus);
            if (!isForkingField(board1, empty2, opposite(nextStatus))) {
                return empty1;
            }

            // try to put into empty2, then test if the opponent cannot fork in empty1
            Board board2 = board.withOverriddenPosition(empty2, nextStatus);
            if (!isForkingField(board2, empty1, opposite(nextStatus))) {
                return empty2;
            }
        }

        // we cannot make a good two-in-a-row
        return null;
    }

    private boolean isForkingField(Board board, Field field, FieldStatus status) {
        OneInARowCollector oneInARowCollector = new OneInARowCollector(status);
        board.iterateOverRows(oneInARowCollector);

        // we check for a potential forks
        List<List<Field>> foundRows = oneInARowCollector.getRowsWithOne();

        for (List<Field> row1 : foundRows) {
            for (List<Field> row2 : foundRows) {
                if (row1 == row2) {
                    continue;
                }

                Field intersection = intersect(row1, row2);
                if (intersection != null && board.isFieldEmpty(intersection) && intersection == field) {
                    return true;
                }
            }
        }

        return false;
    }

}
