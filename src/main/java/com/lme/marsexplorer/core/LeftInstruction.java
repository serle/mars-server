package com.lme.marsexplorer.core;

class LeftInstruction implements Instruction {
    private char token = 'L';

    public RobotState process(GridState gridState, RobotState robotState)  throws ProcessingException {
        Position position = robotState.getPosition();
        Orientation orientation = robotState.getOrientation();
        switch (orientation) {
            case N:
                orientation = Orientation.W;
                break;
            case S:
                orientation = Orientation.E;
                break;
            case E:
                orientation = Orientation.N;
                break;
            case W:
                orientation = Orientation.S;
                break;
        }

        return new RobotState(position, orientation, false);
    }

    public char getToken() {
        return this.token;
    }
}
