package com.topdesk.cases.tictactoe.impl;

import com.topdesk.cases.tictactoe.Field;
import com.topdesk.cases.tictactoe.FieldStatus;
import org.junit.Test;

import static com.topdesk.cases.tictactoe.FieldStatus.*;

/**
 * Contains one PlayerImpl vs PlayerImpl test, and a few pre-recorded game vs an online AI. Every match should end with a draw.
 *
 * @see <a href="http://ostermiller.org/calc/tictactoe.html">the opponent used for test cases</a>
 */
public class PlayerVsPlayerTest {

    private static final FieldStatus _ = EMPTY;

    @Test
    public void playerImplVsPlayerImpl_draw() {
        GameDriver gameDriver = GameDriver.newGame();
        for (int i = 0; i < 9; i++) {
            gameDriver.doMove();
        }
        gameDriver.assertPosition(new FieldStatus[][]{
                {O, X, O},
                {O, X, X},
                {X, O, X}
        });
    }

    @Test
    public void playerImplVsOstermiller1_draw() {
        GameDriver gameDriver = GameDriver.newGame();

        gameDriver
                .move(Field.CENTRE_RIGHT)
                .doMove()
                .move(Field.BOTTOM_CENTRE)
                .doMove()
                .move(Field.TOP_LEFT)
                .doMove()
                .move(Field.BOTTOM_LEFT)
                .doMove()
                .move(Field.TOP_CENTRE);

        gameDriver.assertPosition(new FieldStatus[][]{
                {X, X, O},
                {O, O, X},
                {X, X, O}
        });
    }

    @Test
    public void playerImplVsOstermiller2_draw() {
        GameDriver gameDriver = GameDriver.newGame();

        gameDriver
                .move(Field.TOP_RIGHT)
                .doMove()
                .move(Field.CENTRE_RIGHT)
                .doMove()
                .move(Field.TOP_LEFT)
                .doMove()
                .move(Field.BOTTOM_CENTRE)
                .doMove()
                .move(Field.CENTRE_LEFT);
        ;

        gameDriver.assertPosition(new FieldStatus[][]{
                {X, O, X},
                {X, O, X},
                {O, X, O}
        });
    }

    @Test
    public void playerImplVsOstermiller3_draw() {
        GameDriver gameDriver = GameDriver.newGame();

        gameDriver
                .move(Field.CENTRE_CENTRE)
                .doMove()
                .move(Field.TOP_RIGHT)
                .doMove()
                .move(Field.CENTRE_LEFT)
                .doMove()
                .move(Field.BOTTOM_CENTRE)
                .doMove()
                .move(Field.BOTTOM_RIGHT);
        ;

        gameDriver.assertPosition(new FieldStatus[][]{
                {O, O, X},
                {X, X, O},
                {O, X, X}
        });
    }

    @Test
    public void playerImplVsOstermiller4_draw() {
        GameDriver gameDriver = GameDriver.newGame();

        gameDriver
                .doMove()
                .move(Field.TOP_LEFT)
                .doMove()
                .move(Field.BOTTOM_LEFT)
                .doMove()
                .move(Field.CENTRE_RIGHT)
                .doMove()
                .move(Field.TOP_CENTRE)
                .doMove()
        ;

        gameDriver.assertPosition(new FieldStatus[][]{
                {O, O, X},
                {X, X, O},
                {O, X, X}
        });
    }
}
