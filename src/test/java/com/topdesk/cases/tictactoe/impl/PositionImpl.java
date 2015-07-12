package com.topdesk.cases.tictactoe.impl;

import com.topdesk.cases.tictactoe.Field;
import com.topdesk.cases.tictactoe.Position;
import com.topdesk.cases.tictactoe.FieldStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A {@link Map}-based {@link Position} implementation for tests.
 */
public class PositionImpl implements Position {

    private static final Field[][] fields = new Field[][]{
            {Field.TOP_LEFT, Field.TOP_CENTRE, Field.TOP_RIGHT},
            {Field.CENTRE_LEFT, Field.CENTRE_CENTRE, Field.CENTRE_RIGHT},
            {Field.BOTTOM_LEFT, Field.BOTTOM_CENTRE, Field.BOTTOM_RIGHT}
    };

    private final Map<Field, FieldStatus> board = new HashMap<Field, FieldStatus>();

    public PositionImpl() {
        init();
    }

    public PositionImpl(FieldStatus[][] initialBoard) {
        if (initialBoard == null || initialBoard.length != 3 || initialBoard[0].length != 3) {
            throw new IllegalArgumentException("Invalid initial board...");
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board.put(fields[i][j], initialBoard[i][j]);
            }
        }
    }


    public void init() {
        for (Field field : Field.values()) {
            board.put(field, FieldStatus.EMPTY);
        }
    }

    @Override
    public FieldStatus getFieldStatus(Field field) {
        return board.get(field);
    }

    public void setFieldStatus(Field field, FieldStatus status) {
        board.put(field, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PositionImpl position = (PositionImpl) o;

        return Objects.equals(board, position.board);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(board);
    }

    @Override
    public String toString() {
        return str(board.get(Field.TOP_LEFT)) + str(board.get(Field.TOP_CENTRE)) + str(board.get(Field.TOP_RIGHT)) + "\n" +
                str(board.get(Field.CENTRE_LEFT)) + str(board.get(Field.CENTRE_CENTRE)) + str(board.get(Field.CENTRE_RIGHT)) + "\n" +
                str(board.get(Field.BOTTOM_LEFT)) + str(board.get(Field.BOTTOM_CENTRE)) + str(board.get(Field.BOTTOM_RIGHT));
    }

    private String str(FieldStatus fieldStatus) {
        return fieldStatus == FieldStatus.EMPTY ? "_" : fieldStatus.name();
    }
}
