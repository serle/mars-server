package com.lme.marsexplorer.core;

public class RobotState {
    public boolean isLost;
    public Position position;
    public Orientation orientation;

    //stateStr: "1 1 E", check if positions within bounds
    public RobotState(String stateStr, GridExtent extent) throws InputParseException {

    }

    //used by processors to create the next state
    public RobotState(Position position, Orientation orientation, boolean isLost) {

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
}
