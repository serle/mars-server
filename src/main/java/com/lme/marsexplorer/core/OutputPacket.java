package com.lme.marsexplorer.core;

import java.util.ArrayList;

class OutputPacket {
    private ArrayList<RobotState> robotStates;

    OutputPacket(ArrayList<RobotState> robotStates) {
        this.robotStates = robotStates;
    }

    public void addRobotState(RobotState rs) {
        robotStates.add(rs);
    }

    //converts the set of internal robot states into the output report
    public String toString() {
        StringBuilder sb = new StringBuilder(robotStates.size());

        for (RobotState robotState : robotStates) {
            sb.append(robotState.toString());
            sb.append("\n");
        }

        return sb.toString().trim();
    }
}
