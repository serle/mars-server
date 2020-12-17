package com.lme.marsexplorer.core;

import java.util.HashMap;

import com.lme.marsexplorer.core.instruction.Instruction;


public class RobotCommand {
    private static HashMap<Character, Instruction> instructionMap = new HashMap<>();

    private RobotState initialState;
    private String instructions;

    
    public static void addInstruction(Instruction instruction) {
        instructionMap.put(instruction.getToken(), instruction);
    }

    public static void removeInstruction(Instruction instruction) {
        instructionMap.remove(instruction.getToken());
    }

    public static boolean hasInstruction(Character token) {
        return instructionMap.containsKey(token);
    }


    //first line (position/orientation): "1 1 E"
    //second line (robot instructions): "RFRFRF"
    //extent (grid extent)
    //should throw if position coordinates out of bounds given the grid extent
    //should throw if there are any command thant have not been added i.e. invalid
    public RobotCommand(String firstLine, String secondLine, GridExtent extent) throws InputParseException {
        //todo
    }

    //executes the instructions against the initialState, given the current grid state.
    //returns a new robot state and mutates the grid state with smells as necessary
    public RobotState execute(GridState gridState) {
        //todo
        return null;
    }

    //converts the command back into its original string
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(initialState.toString());
        sb.append("\n");
        sb.append(instructions);
        sb.append("\n");

        return sb.toString();
    }
}
