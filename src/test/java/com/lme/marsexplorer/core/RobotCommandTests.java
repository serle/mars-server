package com.lme.marsexplorer.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


@SpringBootTest
public class RobotCommandTests {

    @Test
    void parse_valid_robot_command_string() {
        // given
        String firstLine = "1 1 E";
        String secondLine = "RFRFRFRF";
        GridExtent extent = new GridExtent(3, 5);

        //then
        assertThatCode(() -> {
            RobotCommand command = new RobotCommand(firstLine, secondLine, extent);
            assertThat(command.toString()).isEqualTo("1 1 E\nRFRFRFRF\n");
        }).doesNotThrowAnyException();
    }
}
