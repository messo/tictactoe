package com.topdesk.cases.tictactoe.impl;

import com.topdesk.cases.tictactoe.Field;
import com.topdesk.cases.tictactoe.FieldStatus;
import com.topdesk.cases.tictactoe.Position;

/**
 * A {@link Position} implementation which wraps around an other {@link Position}, but overrides a field with a new status.
 */
public class OverriddenPosition implements Position {

    private final Position position;
    private final Field overriddenField;
    private final FieldStatus newStatus;


    /**
     * Constructs a position with the given field having the given new status, everything else is unchanged.
     *
     * @param position        the original position
     * @param overriddenField the field whose status should be overridden
     * @param newStatus       the new status
     */
    public OverriddenPosition(Position position, Field overriddenField, FieldStatus newStatus) {
        this.position = position;
        this.overriddenField = overriddenField;
        this.newStatus = newStatus;
    }


    @Override
    public FieldStatus getFieldStatus(Field field) {
        if (this.overriddenField == field) {
            return newStatus;
        } else {
            return position.getFieldStatus(field);
        }
    }
}
