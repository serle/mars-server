package com.lme.marsexplorer.core;

import com.lme.marsexplorer.core.instruction.ForwardInstruction;
import com.lme.marsexplorer.core.instruction.LeftInstruction;
import com.lme.marsexplorer.core.instruction.RightInstruction;

import java.util.ArrayList;

public class Planet {
    public static void addInstructions() {
        RobotCommand.addInstruction(new LeftInstruction());
        RobotCommand.addInstruction(new RightInstruction());
        RobotCommand.addInstruction(new ForwardInstruction());
    }

    public static String execute(String command) throws InputParseException, ProcessingException {
        //parse the command string into its internal representation
        InputPacket input = new InputPacket(command);

        //execute the planet-wide input and create a planet-wide output
        GridState gridState = input.getGridState();
        ArrayList<RobotState> robotStates = new ArrayList<>();
        for (RobotCommand rc : input.getRobotCommands()) {
            RobotState finalState = rc.execute(gridState);
            robotStates.add(finalState);
        }
        OutputPacket output = new OutputPacket(robotStates);

        //return the output report
        return output.toString();
    }
}
