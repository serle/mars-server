package com.lme.marsexplorer.core.instruction;

import com.lme.marsexplorer.core.GridState;
import com.lme.marsexplorer.core.ProcessingException;
import com.lme.marsexplorer.core.RobotState;

public interface Instruction {
    //returns the character that identifies this processor
    char getToken();

    //given the current state and an instruction token, return the next state
    RobotState process(GridState gridState, RobotState robotState) throws ProcessingException;
}
