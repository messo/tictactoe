package com.topdesk.cases.tictactoe.impl;

import com.topdesk.cases.tictactoe.Field;
import com.topdesk.cases.tictactoe.FieldStatus;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PlayerImplTest {

    private static final FieldStatus _ = FieldStatus.EMPTY;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    // ----------------
    // Game is finished
    // ----------------

    @Test
    public void xWon() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Game is finished");

        GameDriver.newGame()
                .givenInitialPosition(new FieldStatus[][]{
                        {FieldStatus.X, FieldStatus.X, FieldStatus.X},
                        {_, FieldStatus.O, _},
                        {_, _, FieldStatus.O}
                })
                .askMove();
    }

    @Test
    public void oWon() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Game is finished");

        GameDriver.newGame()
                .givenInitialPosition(new FieldStatus[][]{
                        {FieldStatus.O, _, FieldStatus.X},
                        {FieldStatus.O, FieldStatus.X, FieldStatus.X},
                        {FieldStatus.O, _, _}
                })
                .askMove();
    }

    @Test
    public void boardIsFull() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Game is finished");

        GameDriver.newGame()
                .givenInitialPosition(new FieldStatus[][]{
                        {FieldStatus.O, FieldStatus.X, FieldStatus.X},
                        {FieldStatus.X, FieldStatus.O, FieldStatus.O},
                        {FieldStatus.O, FieldStatus.X, FieldStatus.X}
                })
                .askMove();
    }

    @Test
    public void illegalPosition() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("The current position is invalid");

        GameDriver.newGame()
                .givenInitialPosition(new FieldStatus[][]{
                        {FieldStatus.O, _, _},
                        {_, _, _},
                        {_, _, _}
                })
                .askMove();
    }

    // ----------------
    // First step tests
    // ----------------

    @Test
    public void startsAtCenter() {
        GameDriver.newGame()
                .askMove()
                .assertMove(Field.CENTRE_CENTRE);
    }

    @Test
    public void cornerResponse() {
        GameDriver.newGame()
                .givenInitialPosition(new FieldStatus[][]{
                        {FieldStatus.X, _, _},
                        {_, _, _},
                        {_, _, _}
                })
                .askMove()
                .assertMove(Field.CENTRE_CENTRE);
    }

    @Test
    public void sideResponse() {
        GameDriver.newGame()
                .givenInitialPosition(new FieldStatus[][]{
                        {_, FieldStatus.X, _},
                        {_, _, _},
                        {_, _, _}
                })
                .askMove()
                .assertMove(Field.CENTRE_CENTRE);
    }

    @Test
    public void centerResponse() {
        GameDriver.newGame()
                .givenInitialPosition(new FieldStatus[][]{
                        {_, _, _},
                        {_, FieldStatus.X, _},
                        {_, _, _}
                })
                .askMove()
                .assertMove(Field.TOP_LEFT);
    }

    // ----------------
    // Winning steps
    // ----------------

    @Test
    public void horizontalWin() {
        GameDriver.newGame()
                .givenInitialPosition(new FieldStatus[][]{
                        {_, FieldStatus.X, FieldStatus.X},
                        {FieldStatus.O, FieldStatus.O, _},
                        {_, _, _}
                })
                .askMove()
                .assertMove(Field.TOP_LEFT)
                .givenInitialPosition(new FieldStatus[][]{
                        {FieldStatus.O, FieldStatus.O, _},
                        {FieldStatus.X, FieldStatus.X, _},
                        {_, _, _}
                })
                .askMove()
                .assertMove(Field.CENTRE_RIGHT)
                .givenInitialPosition(new FieldStatus[][]{
                        {FieldStatus.O, FieldStatus.O, _},
                        {_, _, _},
                        {FieldStatus.X, _, FieldStatus.X}
                })
                .askMove()
                .assertMove(Field.BOTTOM_CENTRE);
    }

    @Test
    public void verticalWin() {
        GameDriver.newGame()
                .givenInitialPosition(new FieldStatus[][]{
                        {FieldStatus.X, _, _},
                        {_, FieldStatus.O, _},
                        {FieldStatus.X, FieldStatus.O, _}
                })
                .askMove()
                .assertMove(Field.CENTRE_LEFT)
                .givenInitialPosition(new FieldStatus[][]{
                        {_, FieldStatus.X, _},
                        {FieldStatus.O, FieldStatus.X, _},
                        {FieldStatus.O, _, _}
                })
                .askMove()
                .assertMove(Field.BOTTOM_CENTRE)
                .givenInitialPosition(new FieldStatus[][]{
                        {_, _, _},
                        {FieldStatus.O, _, FieldStatus.X},
                        {FieldStatus.O, _, FieldStatus.X}
                })
                .askMove()
                .assertMove(Field.TOP_RIGHT);
    }

    @Test
    public void diagonalWin() {
        GameDriver.newGame()
                .givenInitialPosition(new FieldStatus[][]{
                        {_, _, FieldStatus.X},
                        {_, FieldStatus.X, FieldStatus.O},
                        {_, FieldStatus.O, _}
                })
                .askMove()
                .assertMove(Field.BOTTOM_LEFT)
                .givenInitialPosition(new FieldStatus[][]{
                        {_, _, _},
                        {FieldStatus.O, FieldStatus.X, FieldStatus.O},
                        {_, _, FieldStatus.X}
                })
                .askMove()
                .assertMove(Field.TOP_LEFT);
    }

    // ----------------
    // Blocking the winning steps
    // ----------------

    @Test
    public void horizontalBlock() {
        GameDriver.newGame()
                .givenInitialPosition(new FieldStatus[][]{
                        {_, FieldStatus.O, FieldStatus.O},
                        {FieldStatus.X, _, _},
                        {_, FieldStatus.X, _}
                })
                .askMove()
                .assertMove(Field.TOP_LEFT)
                .givenInitialPosition(new FieldStatus[][]{
                        {FieldStatus.X, _, _},
                        {FieldStatus.O, FieldStatus.O, _},
                        {FieldStatus.X, _, _}
                })
                .askMove()
                .assertMove(Field.CENTRE_RIGHT)
                .givenInitialPosition(new FieldStatus[][]{
                        {FieldStatus.X, _, _},
                        {_, FieldStatus.X, _},
                        {FieldStatus.O, _, FieldStatus.O}
                })
                .askMove()
                .assertMove(Field.BOTTOM_CENTRE);
    }

    @Test
    public void verticalBlock() {
        GameDriver.newGame()
                .givenInitialPosition(new FieldStatus[][]{
                        {FieldStatus.O, _, _},
                        {_, FieldStatus.X, _},
                        {FieldStatus.O, _, FieldStatus.X}
                })
                .askMove()
                .assertMove(Field.CENTRE_LEFT)
                .givenInitialPosition(new FieldStatus[][]{
                        {_, FieldStatus.O, _},
                        {FieldStatus.X, FieldStatus.O, FieldStatus.X},
                        {_, _, _}
                })
                .askMove()
                .assertMove(Field.BOTTOM_CENTRE)
                .givenInitialPosition(new FieldStatus[][]{
                        {FieldStatus.X, _, _},
                        {_, _, FieldStatus.O},
                        {_, FieldStatus.X, FieldStatus.O}
                })
                .askMove()
                .assertMove(Field.TOP_RIGHT);
    }

    @Test
    public void diagonalBlock() {
        GameDriver.newGame()
                .givenInitialPosition(new FieldStatus[][]{
                        {FieldStatus.X, _, FieldStatus.O},
                        {_, FieldStatus.O, FieldStatus.X},
                        {_, _, _}
                })
                .askMove()
                .assertMove(Field.BOTTOM_LEFT)
                .givenInitialPosition(new FieldStatus[][]{
                        {_, _, FieldStatus.X},
                        {_, FieldStatus.O, _},
                        {FieldStatus.X, _, FieldStatus.O}
                })
                .askMove()
                .assertMove(Field.TOP_LEFT);
    }

    // ----------------
    // Forking and blocking the forking
    // ----------------

    @Test
    public void fork() {
        GameDriver.newGame()
                .givenInitialPosition(new FieldStatus[][]{
                        {FieldStatus.X, FieldStatus.O, FieldStatus.O},
                        {_, _, _},
                        {_, FieldStatus.X, _}
                })
                .askMove()
                .assertMove(Field.BOTTOM_LEFT)
                .givenInitialPosition(new FieldStatus[][]{
                        {_, FieldStatus.O, _},
                        {FieldStatus.O, FieldStatus.X, FieldStatus.X},
                        {_, _, _}
                })
                .askMove()
                .assertMove(Field.BOTTOM_RIGHT);
    }

    @Test
    public void forkBlock_offensive() {
        GameDriver.newGame()
                .givenInitialPosition(new FieldStatus[][]{
                        {FieldStatus.O, FieldStatus.X, FieldStatus.X},
                        {_, _, _},
                        {_, FieldStatus.O, _}
                })
                .askMove()
                .assertMove(Field.BOTTOM_RIGHT)
                .givenInitialPosition(new FieldStatus[][]{
                        {FieldStatus.X, _, _},
                        {_, FieldStatus.O, _},
                        {_, _, FieldStatus.X}
                })
                .askMove()
                .assertMove(Field.CENTRE_LEFT);
    }

    @Test
    public void forkBlock_defensive() {
        GameDriver.newGame()
                .givenInitialPosition(new FieldStatus[][]{
                        {FieldStatus.X, FieldStatus.O, _},
                        {_, _, _},
                        {_, FieldStatus.X, _}
                })
                .askMove()
                .assertMove(Field.BOTTOM_LEFT);
    }

    // ----------------

    @Test
    public void oppositeCorner() {
        GameDriver.newGame()
                .givenInitialPosition(new FieldStatus[][]{
                        {FieldStatus.O, _, _},
                        {_, FieldStatus.X, _},
                        {_, _, _}
                })
                .askMove()
                .assertMove(Field.BOTTOM_RIGHT)
                .givenInitialPosition(new FieldStatus[][]{
                        {_, _, FieldStatus.O},
                        {_, FieldStatus.X, _},
                        {_, _, _}
                })
                .askMove()
                .assertMove(Field.BOTTOM_LEFT)
                .givenInitialPosition(new FieldStatus[][]{
                        {_, _, _},
                        {_, FieldStatus.X, _},
                        {_, _, FieldStatus.O}
                })
                .askMove()
                .assertMove(Field.TOP_LEFT)
                .givenInitialPosition(new FieldStatus[][]{
                        {_, _, _},
                        {_, FieldStatus.X, _},
                        {FieldStatus.O, _, _}
                })
                .askMove()
                .assertMove(Field.TOP_RIGHT);
    }

    @Test
    public void emptySide() {
        GameDriver.newGame()
                .givenInitialPosition(new FieldStatus[][]{
                        {FieldStatus.O, FieldStatus.O, FieldStatus.X},
                        {FieldStatus.X, FieldStatus.X, FieldStatus.O},
                        {FieldStatus.O, _, FieldStatus.X}
                })
                .askMove()
                .assertMove(Field.BOTTOM_CENTRE);
    }
}
