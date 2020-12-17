package com.lme.marsexplorer.core;

public class ForwardInstruction implements Instruction{
    private char token = 'F';

    public RobotState process(GridState gridState, RobotState robotState)  throws ProcessingException {
        Position position = robotState.getPosition();
        Orientation orientation = robotState.getOrientation();

        int x = position.getX();
        int y = position.getY();
        switch (robotState.getOrientation()) {
            case N:
                y += 1;
                break;
            case S:
                y -= 1;
                break;
            case E:
                x += 1;
                break;
            case W:
                x -= 1;
                break;
        }

        if (gridState.isSmellAt(x, y)) {
            //do nothing
            return robotState;
        }
        else if (gridState.isOnGrid(x, y)) {
            //move forward
            return new RobotState(x, y, orientation, false);
        }
        else {
            //add the smell, and return null
            gridState.addSmellAt(x, y);
            return new RobotState(position, orientation, true);
        }
    }

    public char getToken() {
        return this.token;
    }
}
