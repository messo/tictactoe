package com.topdesk.cases.tictactoe.impl;

import com.google.common.base.Preconditions;
import com.topdesk.cases.tictactoe.Position;
import com.topdesk.cases.tictactoe.impl.rule.*;
import com.topdesk.cases.tictactoe.Field;
import com.topdesk.cases.tictactoe.Player;
import com.topdesk.cases.tictactoe.Rule;

/**
 * Provides an implementation for the {@link Player} interface. It uses the same rule ordering method as Newell and Simon's (1972)
 * tic-tac-toe: it iterates over a finite number of rules ({@link Rule} implementations) and it fires the first one whose conditions
 * are met.
 *
 * @see <a href="http://onlinelibrary.wiley.com/doi/10.1207/s15516709cog1704_3/epdf">Flexible Strategy Use in Young Children’s Tic-Tat-Toe by K. Crowley and R. S. Siegler</a>
 */
public class PlayerImpl implements Player {

    private static final Rule[] orderedRules = new Rule[]{
            new Win(),
            new BlockWin(),
            new Fork(),
            new BlockFork(),
            new EmptyCenter(),
            new OppositeCorner(),
            new EmptyCorner(),
            new EmptySide()
    };

    @Override
    public Field move(Position position) {
        Preconditions.checkNotNull(position);

        Board board = new Board(position);
        if (board.isGameFinished()) {
            throw new IllegalStateException("Game is finished");
        }

        for (Rule rule : orderedRules) {
            Field move = rule.moveIfAvailable(board);
            if (move != null) {
                return move;
            }
        }

        throw new RuntimeException("No rule could be applied... :(");
    }
}
