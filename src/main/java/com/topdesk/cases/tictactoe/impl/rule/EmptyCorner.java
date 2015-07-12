package com.topdesk.cases.tictactoe.impl.rule;

import com.topdesk.cases.tictactoe.Field;
import com.topdesk.cases.tictactoe.impl.Board;

/**
 * <p>The <b>Play Empty Corner</b> rule.</p>
 * <p><i>If</i> there is an empty corner,<br />
 * <i>Then</i> move to the empty corner.</p>
 */
public class EmptyCorner extends AbstractRule {

    @Override
    public Field moveIfAvailable(Board board) {
        return moveIfEmpty(board, Field.TOP_LEFT, Field.TOP_RIGHT, Field.BOTTOM_LEFT, Field.BOTTOM_RIGHT);
    }

}
