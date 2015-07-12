package com.topdesk.cases.tictactoe.impl;

import com.topdesk.cases.tictactoe.Field;
import com.topdesk.cases.tictactoe.FieldStatus;
import com.topdesk.cases.tictactoe.RowHandler;

import java.util.List;

/**
 * A {@link RowHandler} implementation which is looking for a row where there are three pieces of the same type.
 * This is used to determine if one of the players has won.
 */
public class ThreeInARow implements RowHandler {

    @Override
    public boolean onRow(Board board, List<Field> row) {
        int x = 0;
        int o = 0;
        for (Field field : row) {
            if (board.fieldHasStatus(field, FieldStatus.X)) {
                x++;
            }
            if (board.fieldHasStatus(field, FieldStatus.O)) {
                o++;
            }
        }

        return x == 3 || o == 3;
    }
}
