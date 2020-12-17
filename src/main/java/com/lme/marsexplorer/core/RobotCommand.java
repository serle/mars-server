package com.lme.marsexplorer.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

import com.lme.marsexplorer.core.instruction.Instruction;


class RobotCommand {
    static private int MAX_INSTRUCTIONS_LENGTH = 100;
    static private HashMap<Character, Instruction> instructionMap = new HashMap<>();

    static private ArrayList<Character> toInstructionSequence(String instructions) throws InputParseException {
        if (instructions == null || instructions.isEmpty()) throw new InputParseException("No instruction string");
        if (instructions.length() > MAX_INSTRUCTIONS_LENGTH) throw new InputParseException("Instruction string length exceeded");

        ArrayList<Character> result = new ArrayList<>();
        HashSet<Character> invalids = new HashSet<>();
        for (char token : instructions.toCharArray()) {
            if (instructionMap.containsKey(token)) {
                result.add(token);
            }
            else {
                invalids.add(token);
            }
        }

        if (!invalids.isEmpty()) throw new InputParseException(String.format("Invalid robot instructions: %s", invalids.toString()));

        return result;
    }


    /* we want a global instruction registration, so did not use builder pattern */
    static public void addInstruction(Instruction instruction) {
        instructionMap.put(instruction.getToken(), instruction);
    }

    static public void removeInstruction(char instructionToken) {
        instructionMap.remove(instructionToken);
    }

    static public boolean hasInstruction(char token) {
        return instructionMap.containsKey(token);
    }

    static public String getInstructionSet() {
        return instructionMap.keySet().toString();
    }

    //------------------------------------------------------------------------------------------------------------------

    private RobotState initialState;
    private ArrayList<Character> instructions;

    //first line (position/orientation): "1 1 E"
    //second line (robot instructions): "RFRFRF"
    public RobotCommand(String firstLine, String secondLine, GridExtent extent) throws InputParseException {
        this.initialState = new RobotState(firstLine, extent);
        this.instructions = RobotCommand.toInstructionSequence(secondLine);
    }

    //executes the instructions against the initialState, given the current grid state.
    //returns a new robot state and mutates the grid state with smells as necessary
    public RobotState execute(GridState gridState) throws ProcessingException {
        RobotState robotState = this.initialState;
        for (Character token: this.instructions) {
            Instruction instruction = RobotCommand.instructionMap.get(token);
            robotState = instruction.process(gridState, robotState);
        }
        return robotState;
    }

    //converts the command back into its original string
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(initialState.toString());
        sb.append("\n");
        sb.append(instructions.stream().map(Object::toString).collect(Collectors.joining("")));
        sb.append("\n");

        return sb.toString();
    }
}
