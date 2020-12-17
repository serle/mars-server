package com.lme.marsexplorer.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


@SpringBootTest
public class RobotStateTests {

    @Test
    void parse_valid_robot_state_string() {
        // given
        String stateString = "1 1 E";
        GridExtent extent = new GridExtent(3, 5);

        //then
        assertThatCode(() -> {
            RobotState rs = new RobotState(stateString, extent);
            assertThat(rs.toString()).isEqualTo("1 1 E");
        }).doesNotThrowAnyException();
    }


    @Test
    void parse_invalid_robot_orientation() {
        // given
        String stateString = "1 1 F";
        GridExtent extent = new GridExtent(3, 5);

        //then
        assertThatExceptionOfType(InvalidPositionException.class).isThrownBy(() -> new RobotState(stateString, extent))
                .withMessage("%s", "Invalid robot orientation");

    }

    @Test
    void parse_invalid_robot_coordinates() {
        // given
        String stateString = "-1 20 E";
        GridExtent extent = new GridExtent(3, 5);

        //then
        assertThatExceptionOfType(InputParseException.class).isThrownBy(() -> new RobotState(stateString, extent));
    }



}
