package com.lme.marsexplorer.core.instruction;

import com.lme.marsexplorer.core.RobotState;

public class ForwardInstruction implements Instruction{
    private char token = 'F';

    public RobotState process(RobotState state, char instruction) {
        //todo
        return null;
    }

    public char getToken() {
        return this.token;
    }
}
