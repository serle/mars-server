package com.lme.marsexplorer.core;

import java.util.Objects;

public class RobotState {
    private boolean isLost;
    private Position position;
    private Orientation orientation;

    //stateStr: "1 1 E", check if positions within bounds
    public RobotState(String stateStr, GridExtent extent) throws InputParseException {
        if (stateStr == null || stateStr.isEmpty()) throw new InputParseException("No Robot State");

        String[] stateArr = stateStr.split(" ");
        if (stateArr.length != 3) throw new InputParseException("Robot State should contain 2 coordinates and 1 orientation");

        this.isLost = false;
        this.position = new Position(stateArr[0] + " " + stateArr[1], extent);
        this.orientation = Orientation.toOrientation(stateArr[2]);
    }

    //helper method for testing
    public RobotState(String stateStr, GridExtent extent, boolean isLost) throws InputParseException {
        this(stateStr, extent);
        this.isLost = true;
    }

    public RobotState(int x, int y, Orientation orientation, boolean isLost) {
        this.isLost = isLost;
        this.position = new Position(x, y);
        this.orientation = orientation;
    }

    public RobotState(Position position, Orientation orientation, boolean isLost) {
        this.isLost = isLost;
        this.position = position;
        this.orientation = orientation;
    }

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    //return the output string e.g.
    // 1) not lost:   "1 1 E"
    // 2) lost:       "3 3 N LOST"
    public String toString() {
        if (this.isLost) {
            return String.format("%d %d %s LOST", position.getX(), position.getY(), orientation.toString());
        }
        else {
            return String.format("%d %d %s", position.getX(), position.getY(), orientation.toString());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RobotState)) return false;
        RobotState that = (RobotState) o;
        return isLost == that.isLost &&
                position.equals(that.position) &&
                orientation == that.orientation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isLost, position, orientation);
    }
}
