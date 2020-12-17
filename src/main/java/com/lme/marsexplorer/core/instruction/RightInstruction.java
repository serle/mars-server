package com.lme.marsexplorer.core.instruction;

import com.lme.marsexplorer.core.RobotState;

public class RightInstruction implements Instruction {
    private char token = 'R';

    public RobotState process(RobotState state, char instruction) {
        //todo
        return null;
    }

    public char getToken() {
        return this.token;
    }
}
