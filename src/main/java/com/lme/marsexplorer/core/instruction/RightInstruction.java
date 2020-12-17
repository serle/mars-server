package com.lme.marsexplorer.core.instruction;

import com.lme.marsexplorer.core.GridState;
import com.lme.marsexplorer.core.ProcessingException;
import com.lme.marsexplorer.core.RobotState;

public class RightInstruction implements Instruction {
    private char token = 'R';

    public RobotState process(GridState gridState, RobotState robotState)  throws ProcessingException {
        //todo
        throw new ProcessingException("Right: not Implemented yet");
    }

    public char getToken() {
        return this.token;
    }
}
