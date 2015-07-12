package com.topdesk.cases.tictactoe.impl;

import com.topdesk.cases.tictactoe.Field;
import com.topdesk.cases.tictactoe.FieldStatus;
import com.topdesk.cases.tictactoe.Player;
import org.junit.Assert;

/**
 * A driver for an actual game for readable tests.
 */
public class GameDriver {

    private PositionImpl position;
    private Field lastMove;

    private FieldStatus nextStatus;

    private GameDriver() {
        position = new PositionImpl();
        nextStatus = FieldStatus.X;
    }


    public static GameDriver newGame() {
        return new GameDriver();
    }


    private Player getPlayer() {
        return new PlayerImpl();
    }

    public GameDriver givenInitialPosition(FieldStatus[][] board) {
        position = new PositionImpl(board);
        return this;
    }

    public GameDriver askMove() {
        lastMove = getPlayer().move(position);
        return this;
    }

    public GameDriver doMove() {
        lastMove = getPlayer().move(position);
        position.setFieldStatus(lastMove, nextStatus);
        nextStatus = nextStatus == FieldStatus.X ? FieldStatus.O : FieldStatus.X;
        return this;
    }

    public GameDriver move(Field field) {
        position.setFieldStatus(field, nextStatus);
        nextStatus = nextStatus == FieldStatus.X ? FieldStatus.O : FieldStatus.X;
        return this;
    }

    public GameDriver assertMove(Field expected) {
        Assert.assertEquals(expected, lastMove);
        return this;
    }

    public void assertPosition(FieldStatus[][] board) {
        Assert.assertEquals(new PositionImpl(board), position);
    }
}
