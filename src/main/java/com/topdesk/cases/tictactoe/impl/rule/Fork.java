package com.topdesk.cases.tictactoe.impl.rule;

import com.topdesk.cases.tictactoe.Field;
import com.topdesk.cases.tictactoe.impl.Board;
import com.topdesk.cases.tictactoe.impl.OneInARowCollector;
import com.topdesk.cases.tictactoe.FieldStatus;

import java.util.List;

/**
 * <p>The <b>Fork</b> rule.</p>
 * <p><i>If</i> there are two intersecting rows, columns, or diagonals with one of my pieces and two blanks, and <br />
 * <i>If</i> the intersecting space is empty, <br />
 * <i>Then</i> move to the intersecting space (thus creating two woys to win on my next turn).</p>
 */
public class Fork extends AbstractRule {

    @Override
    public Field moveIfAvailable(Board board) {
        return getForkingField(board, board.getNextStatus());
    }

    /**
     * Returns an empty field which is located in an intersection of two rows where only one field has the given
     * {@link FieldStatus}, all the others are {@link FieldStatus#EMPTY}.
     *
     * @param board  the board
     * @param status the status we are looking for
     * @return a matching field, or {@code null} if there isn't any field like this
     */
    protected Field getForkingField(Board board, FieldStatus status) {
        OneInARowCollector oneInARowCollector = new OneInARowCollector(status);
        board.iterateOverRows(oneInARowCollector);

        List<List<Field>> foundRows = oneInARowCollector.getRowsWithOne();

        for (List<Field> row1 : foundRows) {
            for (List<Field> row2 : foundRows) {
                if (row1 == row2) {
                    continue;
                }

                Field intersection = intersect(row1, row2);
                if (intersection != null && board.isFieldEmpty(intersection)) {
                    return intersection;
                }
            }
        }

        return null;
    }

}
