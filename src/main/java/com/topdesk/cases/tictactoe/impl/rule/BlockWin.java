package com.topdesk.cases.tictactoe.impl.rule;

import com.topdesk.cases.tictactoe.Field;
import com.topdesk.cases.tictactoe.impl.Board;

/**
 * <p>The <b>Block Win</b> rule.</p>
 * <p><i>If</i> there is a row, column, or diagonal with two of my opponent’s pieces and a blank space,<br />
 * <i>Then</i> play the blank space (thus blocking a potential win for my opponent).</p>
 * <p>Note: basically this is a {@link Win} rule applying as an opponent</p>
 */
public class BlockWin extends Win {

    @Override
    public Field moveIfAvailable(Board board) {
        return getWinningField(board, opposite(board.getNextStatus()));
    }

}
