package com.lme.marsexplorer.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class OutputPacketTests {

    @Test
    void validate_output_string() {
        // given
        RobotCommand.addInstruction(new LeftInstruction());
        RobotCommand.addInstruction(new RightInstruction());
        RobotCommand.addInstruction(new ForwardInstruction());
        GridExtent extent = new GridExtent(5, 3);
        ArrayList<RobotState> robotStates = new ArrayList<>();
        assertThatCode(() -> {
            robotStates.add(new RobotState("1 1 E", extent));
            robotStates.add(new RobotState("3 2 N", extent, true));
            robotStates.add(new RobotState("2 3 S", extent));
        }).doesNotThrowAnyException();
        OutputPacket outputPacket = new OutputPacket(robotStates);

        //then
        assertThat(outputPacket.toString()).isEqualTo("1 1 E\n3 2 N LOST\n2 3 S");
    }

}
