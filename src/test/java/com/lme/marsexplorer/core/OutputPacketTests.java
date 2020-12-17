package com.lme.marsexplorer.core;

import com.lme.marsexplorer.core.instruction.ForwardInstruction;
import com.lme.marsexplorer.core.instruction.LeftInstruction;
import com.lme.marsexplorer.core.instruction.RightInstruction;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class OutputPacketTests {

    @BeforeTestExecution
    public void setUp() {
        RobotCommand.addInstruction(new LeftInstruction());
        RobotCommand.addInstruction(new RightInstruction());
        RobotCommand.addInstruction(new ForwardInstruction());
    }

    @Test
    void validate_output_string() {
        // given
        GridExtent extent = new GridExtent(3, 5);
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
