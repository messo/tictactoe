package com.topdesk.cases.tictactoe.impl.rule;

import com.topdesk.cases.tictactoe.Field;
import com.topdesk.cases.tictactoe.impl.Board;

/**
 * <p>The <b>Play Center</b> rule.</p>
 * <p><i>If</i> the center is blank,<br />
 * <i>Then</i> play the center.</p>
 */
public class EmptyCenter extends AbstractRule {

    @Override
    public Field moveIfAvailable(Board board) {
        return moveIfEmpty(board, Field.CENTRE_CENTRE);
    }

}
