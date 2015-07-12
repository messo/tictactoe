package com.topdesk.cases.tictactoe.impl.rule;

import com.topdesk.cases.tictactoe.Field;
import com.topdesk.cases.tictactoe.impl.Board;
import com.topdesk.cases.tictactoe.FieldStatus;

/**
 * <p>The <b>Play Opposite Corner</b> rule.</p>
 * <p><i>If</i> my opponent is in a corner, and<br />
 * <p><i>If</i> the opposite corner is empty <br />
 * <i>Then</i> play the opposite corner.</p>
 */
public class OppositeCorner extends AbstractRule {

    @Override
    public Field moveIfAvailable(Board board) {
        FieldStatus opposite = opposite(board.getNextStatus());

        if (board.fieldHasStatus(Field.TOP_LEFT, opposite)) {
            return moveIfEmpty(board, Field.BOTTOM_RIGHT);
        }

        if (board.fieldHasStatus(Field.BOTTOM_RIGHT, opposite)) {
            return moveIfEmpty(board, Field.TOP_LEFT);
        }

        if (board.fieldHasStatus(Field.TOP_RIGHT, opposite)) {
            return moveIfEmpty(board, Field.BOTTOM_LEFT);
        }

        if (board.fieldHasStatus(Field.BOTTOM_LEFT, opposite)) {
            return moveIfEmpty(board, Field.TOP_RIGHT);
        }

        return null;
    }

}
