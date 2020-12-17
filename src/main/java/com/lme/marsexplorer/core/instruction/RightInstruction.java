package com.lme.marsexplorer.core.instruction;

import com.lme.marsexplorer.core.*;

public class RightInstruction implements Instruction {
    private char token = 'R';

    public RobotState process(GridState gridState, RobotState robotState)  throws ProcessingException {
        Position position = robotState.getPosition();
        Orientation orientation = robotState.getOrientation();
        switch (orientation) {
            case N:
                orientation = Orientation.E;
                break;
            case S:
                orientation = Orientation.W;
                break;
            case E:
                orientation = Orientation.S;
                break;
            case W:
                orientation = Orientation.N;
                break;
        }

        return new RobotState(position, orientation, false);
    }

    public char getToken() {
        return this.token;
    }
}
