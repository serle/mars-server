package com.lme.marsexplorer.core;

import java.util.ArrayList;

public class Planet {
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
