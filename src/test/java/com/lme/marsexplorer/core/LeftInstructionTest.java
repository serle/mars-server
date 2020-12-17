package com.lme.marsexplorer.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
public class LeftInstructionTest {

    @Test
    void from_north_it_orientates_west() {
        // given
        GridState gridState = new GridState(3, 3);
        RobotState robotState = new RobotState(1, 1, Orientation.N, false);
        LeftInstruction left = new LeftInstruction();

        assertThatCode(() -> assertThat(left.process(gridState, robotState).equals(new RobotState(1, 1, Orientation.W, false)))).doesNotThrowAnyException();
    }

    @Test
    void from_west_it_orientates_south() {
        // given
        GridState gridState = new GridState(3, 3);
        RobotState robotState = new RobotState(1, 1, Orientation.W, false);
        LeftInstruction left = new LeftInstruction();

        assertThatCode(() -> assertThat(left.process(gridState, robotState).equals(new RobotState(1, 1, Orientation.S, false)))).doesNotThrowAnyException();
    }

    @Test
    void from_south_it_orientates_east() {
        // given
        GridState gridState = new GridState(3, 3);
        RobotState robotState = new RobotState(1, 1, Orientation.S, false);
        LeftInstruction left = new LeftInstruction();

        assertThatCode(() -> assertThat(left.process(gridState, robotState).equals(new RobotState(1, 1, Orientation.E, false)))).doesNotThrowAnyException();
    }

    @Test
    void from_east_it_orientates_north() {
        // given
        GridState gridState = new GridState(3, 3);
        RobotState robotState = new RobotState(1, 1, Orientation.E, false);
        LeftInstruction left = new LeftInstruction();

        assertThatCode(() -> assertThat(left.process(gridState, robotState).equals(new RobotState(1, 1, Orientation.N, false)))).doesNotThrowAnyException();
    }


}
