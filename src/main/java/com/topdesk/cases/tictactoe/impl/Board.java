package com.topdesk.cases.tictactoe.impl;

import com.topdesk.cases.tictactoe.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>The board of a Tic-Tac-Toe game. It wraps a {@link Position} instance and provides convenient methods to access the
 * information about the current state of the game.</p>
 * <p>This implementation is immutable, but a modified version of the board can be retrieved.</p>
 */
public class Board {

    private static final List<List<Field>> rows = Arrays.asList(
            Collections.unmodifiableList(Arrays.asList(Field.TOP_LEFT, Field.TOP_CENTRE, Field.TOP_RIGHT)),
            Collections.unmodifiableList(Arrays.asList(Field.CENTRE_LEFT, Field.CENTRE_CENTRE, Field.CENTRE_RIGHT)),
            Collections.unmodifiableList(Arrays.asList(Field.BOTTOM_LEFT, Field.BOTTOM_CENTRE, Field.BOTTOM_RIGHT))
    );
    private static final List<List<Field>> columns = Arrays.asList(
            Collections.unmodifiableList(Arrays.asList(Field.TOP_LEFT, Field.CENTRE_LEFT, Field.BOTTOM_LEFT)),
            Collections.unmodifiableList(Arrays.asList(Field.TOP_CENTRE, Field.CENTRE_CENTRE, Field.BOTTOM_CENTRE)),
            Collections.unmodifiableList(Arrays.asList(Field.TOP_RIGHT, Field.CENTRE_RIGHT, Field.BOTTOM_RIGHT))
    );
    private static final List<Field> mainDiagonal = Collections.unmodifiableList(Arrays.asList(Field.TOP_LEFT, Field.CENTRE_CENTRE, Field.BOTTOM_RIGHT));
    private static final List<Field> antiDiagonal = Collections.unmodifiableList(Arrays.asList(Field.TOP_RIGHT, Field.CENTRE_CENTRE, Field.BOTTOM_LEFT));


    private final Position position;


    /**
     * Constructs a board for a given {@link Position}.
     *
     * @param position position which represents the state of the board
     */
    public Board(Position position) {
        this.position = position;
    }


    /**
     * Determines which {@link FieldStatus} should be used by the {@link Player}.
     *
     * @return the "color" (X/O) of the current player
     * @throws IllegalStateException if the current {@link Position} is invalid: it is valid if the number of Xs
     *                               equals to the number of Os, or if it is only greater by 1
     */
    public FieldStatus getNextStatus() {
        int numberOfX = 0;
        int numberOfO = 0;

        for (Field field : Field.values()) {
            FieldStatus status = position.getFieldStatus(field);
            if (status == FieldStatus.X) {
                numberOfX++;
            } else if (status == FieldStatus.O) {
                numberOfO++;
            }
        }

        if (numberOfX == numberOfO) {
            return FieldStatus.X;
        } else if (numberOfX - 1 == numberOfO) {
            return FieldStatus.O;
        } else {
            throw new IllegalStateException("The current position is invalid");
        }
    }

    /**
     * Checks whether the given field on the board is empty
     *
     * @param field field to check
     * @return {@code true} if the field is empty, {@code false} otherwise
     */
    public boolean isFieldEmpty(Field field) {
        return position.getFieldStatus(field) == FieldStatus.EMPTY;
    }

    /**
     * Checks whether the given field has the given status on the board
     *
     * @param field  field to check
     * @param status status to validate
     * @return {@code true} if the field has the given status, {@code false} otherwise
     */
    public boolean fieldHasStatus(Field field, FieldStatus status) {
        return position.getFieldStatus(field) == status;
    }

    /**
     * Checks whether the game is finished. A game is finished if one of the following conditions are met:
     * <ul>
     * <li>there are no more empty fields</li>
     * <li>there are three pieces of the same type in a row, column or in a diagonal</li>
     * </ul>
     *
     * @return {@code true} if the game is finished, {@code false} otherwise
     */
    public boolean isGameFinished() {
        int emptyFields = 0;
        for (Field field : Field.values()) {
            if (isFieldEmpty(field)) {
                emptyFields++;
            }
        }

        return emptyFields == 0 || someoneHasWon();
    }

    /**
     * Checks whether someone has won the game with three-in-a-row.
     *
     * @return {@code true} if someone won, {@code false} otherwise
     */
    public boolean someoneHasWon() {
        ThreeInARow threeInARow = new ThreeInARow();
        return iterateOverRows(threeInARow);
    }

    /**
     * Iterates over the horizontal, vertical and diagonal rows of the board and invokes the {@link RowHandler} for every row.
     * It stops the iteration if the handler returns with a {@code true} for a row.
     *
     * @param rowHandler the instance which handles a row
     * @return {@code true} if there was a row where the handler handled the row with a return value of {@code true}, {@code false} otherwise
     */
    public boolean iterateOverRows(RowHandler rowHandler) {
        for (int i = 0; i < 3; i++) {
            if (rowHandler.onRow(this, rows.get(i))) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (rowHandler.onRow(this, columns.get(i))) {
                return true;
            }
        }

        return rowHandler.onRow(this, mainDiagonal) || rowHandler.onRow(this, antiDiagonal);
    }

    /**
     * Creates a new Board with a slightly modified {@link Position} where the {@code fieldToOverride} field will have
     * the {@code newStatus} as status, regardless of the actual status. This can be used to simulate actions.
     *
     * @param fieldToOverride the field which status we want to override
     * @param newStatus       the new status
     * @return a modified board
     */
    public Board withOverriddenPosition(Field fieldToOverride, FieldStatus newStatus) {
        return new Board(new OverriddenPosition(position, fieldToOverride, newStatus));
    }
}
