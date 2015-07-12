package com.topdesk.cases.tictactoe.impl.rule;

import com.topdesk.cases.tictactoe.Field;
import com.topdesk.cases.tictactoe.impl.Board;

/**
 * <p>The <b>Play Empty Side</b> rule.</p>
 * <p><i>If</i> there is an empty side,<br />
 * <i>Then</i> move to the empty side.</p>
 */
public class EmptySide extends AbstractRule {

    @Override
    public Field moveIfAvailable(Board board) {
        return moveIfEmpty(board, Field.TOP_CENTRE, Field.CENTRE_LEFT, Field.CENTRE_RIGHT, Field.BOTTOM_CENTRE);
    }

}
