package com.lme.marsexplorer.core;

import java.util.ArrayList;

class InputPacket {
    private GridState gridState;
    private ArrayList<RobotCommand> robotCommands = new ArrayList<>();

    //receives gridState on 1st line, followed by n robot commands separated by a blank line
    public InputPacket(String packetStr) throws InputParseException {
        Object[] lines = packetStr.lines().toArray();

        //process the first line
        this.gridState = new GridState(lines[0].toString());

       //process the remaining lines
        int line_num = 1;
        //track the cmd_num for more specific exception messaging
        int cmd_num = 0;
        while (line_num + 1 < lines.length) {
            try {
                //consume two lines of input and create the associates robot command
                RobotCommand command = new RobotCommand(lines[line_num].toString(), lines[line_num + 1].toString(), gridState);
                //add the new command into the array list
                this.robotCommands.add(command);

                //ensure that after the first two robot command lines we have an empty separator line
                if (line_num + 2 >= lines.length) {
                    //we have processed all the input
                    break;
                } else if (lines[line_num + 2].toString().isEmpty()) {
                    //we can skip ahead to the next robot command lines
                    line_num += 3;
                } else {
                    //we have a non-empty separator line
                    throw new InputParseException("No separator line");
                }
                cmd_num++;
            }
            catch (InputParseException e) {
                throw new InputParseException(String.format("RobotCommand[%d]: %s", cmd_num, e.getMessage()));
            }
        }
    }

    public GridState getGridState() {
        return gridState;
    }

    public ArrayList<RobotCommand> getRobotCommands() {
        return robotCommands;
    }


    //reproduce the input string
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(gridState.getExtent().toString());
        sb.append("\n");

        for (RobotCommand command : robotCommands) {
            sb.append(command.toString());
            sb.append("\n");
        }

        //we trim to get rid of the extra newline at the end
        return sb.toString().trim();
    }

}
