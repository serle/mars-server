package com.lme.marsexplorer.core;

public class ForwardInstruction implements Instruction{
    private char token = 'F';

    public RobotState process(GridState gridState, RobotState robotState)  throws ProcessingException {
        Position position = robotState.getPosition();
        Orientation orientation = robotState.getOrientation();
        boolean isSmelly = gridState.isSmellAt(position);

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

        if (gridState.isOnGrid(x, y)) {
            //move forward
            return new RobotState(x, y, orientation, false);
        }
        else if (gridState.isSmellAt(position)) {
            //there is already a smell at the current position
            return robotState;
        }
        else {
            //no smell at the current position, so add the smell at the current position
            gridState.addSmellAt(position.getX(), position.getY());
            //return a robot state with the current position and a lost flag
            return new RobotState(position, orientation, true);
        }
    }

    public char getToken() {
        return this.token;
    }
}
