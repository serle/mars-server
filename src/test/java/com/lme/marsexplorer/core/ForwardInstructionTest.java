package com.lme.marsexplorer.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


@SpringBootTest
public class ForwardInstructionTest {

    @Test
    void it_moves_north() {
        // given
        GridState gridState = new GridState(3, 3);
        RobotState robotState = new RobotState(1, 1, Orientation.N, false);
        ForwardInstruction forward = new ForwardInstruction();

        assertThatCode(() -> assertThat(forward.process(gridState, robotState).equals(new RobotState(1, 2, Orientation.N, false)))).doesNotThrowAnyException();
    }

    @Test
    void it_moves_south() {
        // given
        GridState gridState = new GridState(3, 3);
        RobotState robotState = new RobotState(1, 1, Orientation.S, false);
        ForwardInstruction forward = new ForwardInstruction();

        assertThatCode(() -> assertThat(forward.process(gridState, robotState).equals(new RobotState(1, 0, Orientation.S, false)))).doesNotThrowAnyException();
    }

    @Test
    void it_moves_east() {
        // given
        GridState gridState = new GridState(3, 3);
        RobotState robotState = new RobotState(1, 1, Orientation.E, false);
        ForwardInstruction forward = new ForwardInstruction();

        assertThatCode(() -> assertThat(forward.process(gridState, robotState).equals(new RobotState(2, 1, Orientation.E, false)))).doesNotThrowAnyException();
    }

    @Test
    void it_moves_west() {
        // given
        GridState gridState = new GridState(3, 3);
        RobotState robotState = new RobotState(1, 1, Orientation.W, false);
        ForwardInstruction forward = new ForwardInstruction();

        assertThatCode(() -> assertThat(forward.process(gridState, robotState).equals(new RobotState(0, 1, Orientation.W, false)))).doesNotThrowAnyException();
    }

    @Test
    void it_is_lost_stays_put_and_records_smell() {
        // given
        GridState gridState = new GridState(3, 3);
        RobotState robotState = new RobotState(0, 0, Orientation.S, false);
        ForwardInstruction forward = new ForwardInstruction();

        assertThatCode(() -> {
            assertThat(forward.process(gridState, robotState).equals(new RobotState(0, 0, Orientation.S, true)));
            assertThat(gridState.isSmellAt(0, 0));
        }).doesNotThrowAnyException();
    }

    @Test
    void it_stays_put_due_to_existing_smell() {
        // given
        GridState gridState = new GridState(3, 3);
        gridState.addSmellAt(0, 0);
        RobotState robotState = new RobotState(0, 0, Orientation.S, false);
        ForwardInstruction forward = new ForwardInstruction();

        assertThatCode(() -> assertThat(forward.process(gridState, robotState).equals(new RobotState(0, 0, Orientation.S, false)))).doesNotThrowAnyException();
    }
}
