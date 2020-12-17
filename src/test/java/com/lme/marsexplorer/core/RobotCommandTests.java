package com.lme.marsexplorer.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;


@SpringBootTest
public class RobotCommandTests {

    private String createString(int length, char fill_char) {
        char[] arr = new char[length];
        Arrays.fill(arr, fill_char);
        return new String(arr);
    }


    @Test
    void add_robot_instructions() {
        assertThatCode(() -> {
            RobotCommand.addInstruction(new LeftInstruction());
            RobotCommand.addInstruction(new RightInstruction());
            RobotCommand.addInstruction(new ForwardInstruction());
            assertThat(RobotCommand.hasInstruction('R')).isTrue();
            assertThat(RobotCommand.hasInstruction('F')).isTrue();
            assertThat(RobotCommand.hasInstruction('L')).isTrue();
        }).doesNotThrowAnyException();
    }

    @Test
    void remove_robot_instructions() {
        RobotCommand.addInstruction(new LeftInstruction());
        RobotCommand.addInstruction(new RightInstruction());
        RobotCommand.addInstruction(new ForwardInstruction());

        assertThatCode(() -> {
            RobotCommand.removeInstruction('F');
            assertThat(RobotCommand.hasInstruction('R')).isTrue();
            assertThat(RobotCommand.hasInstruction('F')).isFalse();
            assertThat(RobotCommand.hasInstruction('L')).isTrue();
        }).doesNotThrowAnyException();
    }

    @Test
    void parse_valid_robot_command_string() {
        // given
        String firstLine = "1 1 E";
        String secondLine = "RFRFRFRF";
        GridState gridState = new GridState(5, 3);
        RobotCommand.addInstruction(new RightInstruction());
        RobotCommand.addInstruction(new ForwardInstruction());

        //then
        assertThatCode(() -> {
            RobotCommand command = new RobotCommand(firstLine, secondLine, gridState);
            assertThat(command.toString()).isEqualTo("1 1 E\nRFRFRFRF\n");
        }).doesNotThrowAnyException();
    }

    @Test
    void parse_invalid_robot_command_string() {
        // given
        String firstLine = "1 1 E";
        String secondLine = "RFRFRFRF";
        GridState gridState = new GridState(5, 3);
        RobotCommand.addInstruction(new RightInstruction());
        RobotCommand.removeInstruction('F');

        //then
        assertThatExceptionOfType(InputParseException.class).isThrownBy(() -> new RobotCommand(firstLine, secondLine, gridState));
    }


    @Test
    void parse_empty_instruction_string() {
        // given
        RobotCommand.addInstruction(new LeftInstruction());
        RobotCommand.addInstruction(new RightInstruction());
        RobotCommand.addInstruction(new ForwardInstruction());

        String firstLine = "1 1 E";
        String secondLine = "";
        GridState gridState = new GridState(5, 3);

        //then
        assertThatExceptionOfType(InputParseException.class).isThrownBy(() -> new RobotCommand(firstLine, secondLine, gridState))
                .withMessage("%s", "No instruction string");
    }

    @Test
    void parse_instruction_string_exceeds_maximum_length() {
        // given
        RobotCommand.addInstruction(new LeftInstruction());
        RobotCommand.addInstruction(new RightInstruction());
        RobotCommand.addInstruction(new ForwardInstruction());

        String firstLine = "1 1 E";
        String secondLine = this.createString(110, 'R');
        GridState gridState = new GridState(5, 3);

        //then
        assertThatExceptionOfType(InputParseException.class).isThrownBy(() -> new RobotCommand(firstLine, secondLine, gridState))
                .withMessage("%s", "Instruction string length exceeded");
    }

    @Test
    void instruction_execution_continues_while_on_grid() {
        // given
        RobotCommand.addInstruction(new LeftInstruction());
        RobotCommand.addInstruction(new RightInstruction());
        RobotCommand.addInstruction(new ForwardInstruction());

        String firstLine = "1 1 E";
        String secondLine = "RFRFRFRF";
        GridState gridState = new GridState(5, 3);

        //then
        assertThatCode(() -> {
            RobotCommand command = new RobotCommand(firstLine, secondLine, gridState);
            RobotState robotState = command.execute(gridState);
            assertThat(robotState.toString()).isEqualTo("1 1 E");
        }).doesNotThrowAnyException();
    }

    @Test
    void instruction_execution_stops_if_lost() {
        // given
        RobotCommand.addInstruction(new LeftInstruction());
        RobotCommand.addInstruction(new RightInstruction());
        RobotCommand.addInstruction(new ForwardInstruction());

        String firstLine = "3 2 N";
        String secondLine = "FRRFLLFFRRFLL";
        GridState gridState = new GridState(5, 3);

        //then
        assertThatCode(() -> {
            RobotCommand command = new RobotCommand(firstLine, secondLine, gridState);
            RobotState robotState = command.execute(gridState);
            assertThat(robotState.toString()).isEqualTo("3 3 N LOST");
        }).doesNotThrowAnyException();
    }

    @Test
    void it_stays_put_when_there_is_a_smell() {
        // given
        RobotCommand.addInstruction(new LeftInstruction());
        RobotCommand.addInstruction(new RightInstruction());
        RobotCommand.addInstruction(new ForwardInstruction());

        String firstLine = "0 3 W";
        String secondLine = "LLFFFLFLFL";
        GridState gridState = new GridState(5, 3);
        gridState.addSmellAt(3, 3);

        //then
        assertThatCode(() -> {
            RobotCommand command = new RobotCommand(firstLine, secondLine, gridState);
            RobotState robotState = command.execute(gridState);
            assertThat(robotState.toString()).isEqualTo("2 3 S");
        }).doesNotThrowAnyException();
    }


}
