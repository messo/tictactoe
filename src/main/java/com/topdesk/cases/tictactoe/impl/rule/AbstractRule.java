package com.topdesk.cases.tictactoe.impl.rule;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import com.topdesk.cases.tictactoe.Field;
import com.topdesk.cases.tictactoe.impl.Board;
import com.topdesk.cases.tictactoe.FieldStatus;
import com.topdesk.cases.tictactoe.Rule;

import java.util.List;

/**
 * An abstract base class for {@link Rule} implementations, providing some helper methods, which can be used by
 * concrete implementations.
 */
public abstract class AbstractRule implements Rule {

    /**
     * Returns the opposite type of a piece
     *
     * @param status a type of a piece (it cannot be {@link FieldStatus#EMPTY})
     * @return the opposite type (X for O and O for X)
     * @throws IllegalArgumentException if the {@code status} is neither {@link FieldStatus#X} nor {@link FieldStatus#O}
     */
    protected FieldStatus opposite(FieldStatus status) {
        if (status == FieldStatus.O) {
            return FieldStatus.X;
        } else if (status == FieldStatus.X) {
            return FieldStatus.O;
        } else {
            throw new IllegalArgumentException("Status must be either X or O!");
        }
    }

    /**
     * Determines the intersection of two different rows.
     *
     * @param row1 row#1
     * @param row2 row#2
     * @return the field which is the intersection, or {@code null} if the rows are perpendicular.
     */
    protected Field intersect(List<Field> row1, List<Field> row2) {
        Preconditions.checkArgument(row1 != row2, "A row cannot be intersected with itself.");
        Sets.SetView<Field> intersection = Sets.intersection(Sets.newHashSet(row1), Sets.newHashSet(row2));
        if (intersection.size() == 1) {
            return intersection.iterator().next();
        } else {
            return null;
        }
    }

    /**
     * Returns the first empty field from the given list
     *
     * @param board         the board
     * @param testsForEmpty the fields to test
     * @return the first empty field, {@code null} if none of them is empty
     */
    protected Field moveIfEmpty(Board board, Field... testsForEmpty) {
        for (Field testForEmpty : testsForEmpty) {
            Field field = moveIfEmpty(board, testForEmpty);
            if (field != null) {
                return field;
            }
        }

        return null;
    }

    /**
     * Returns the field if it is empty
     *
     * @param board        the board
     * @param testForEmpty the field to test
     * @return the field if it is empty, {@code null} otherwise
     */
    protected Field moveIfEmpty(Board board, Field testForEmpty) {
        if (board.isFieldEmpty(testForEmpty)) {
            return testForEmpty;
        } else {
            return null;
        }
    }

}
