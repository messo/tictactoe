package com.topdesk.cases.tictactoe;

import com.topdesk.cases.tictactoe.impl.Board;

/**
 * A rule for the conflict resolution.
 */
public interface Rule {

    /**
     * Checks if the rule can be applied for the given {@link Board} and if yes, it plays the appropriate move
     *
     * @param board the current board
     * @return the field on which the player makes its next move if the given rule can be applied for the current state of the game
     */
    Field moveIfAvailable(Board board);

}
