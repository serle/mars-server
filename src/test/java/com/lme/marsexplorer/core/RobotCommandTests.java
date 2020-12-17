package com.lme.marsexplorer.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.lme.marsexplorer.core.instruction.ForwardInstruction;
import com.lme.marsexplorer.core.instruction.LeftInstruction;
import com.lme.marsexplorer.core.instruction.RightInstruction;


@SpringBootTest
public class RobotCommandTests {

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
        GridExtent extent = new GridExtent(3, 5);
        RobotCommand.addInstruction(new RightInstruction());
        RobotCommand.addInstruction(new ForwardInstruction());

        //then
        assertThatCode(() -> {
            RobotCommand command = new RobotCommand(firstLine, secondLine, extent);
            assertThat(command.toString()).isEqualTo("1 1 E\nRFRFRFRF\n");
        }).doesNotThrowAnyException();
    }

    @Test
    void parse_invalid_robot_command_string() {
        // given
        String firstLine = "1 1 E";
        String secondLine = "RFRFRFRF";
        GridExtent extent = new GridExtent(3, 5);
        RobotCommand.addInstruction(new RightInstruction());
        RobotCommand.removeInstruction('F');

        //then
        assertThatExceptionOfType(InputParseException.class).isThrownBy(() -> new RobotCommand(firstLine, secondLine, extent));
    }
}
