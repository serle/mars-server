package com.lme.marsexplorer.core;

import com.lme.marsexplorer.core.instruction.ForwardInstruction;
import com.lme.marsexplorer.core.instruction.LeftInstruction;
import com.lme.marsexplorer.core.instruction.RightInstruction;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PlanetTests {

    @BeforeTestExecution
    public void setUp() {
        RobotCommand.addInstruction(new LeftInstruction());
        RobotCommand.addInstruction(new RightInstruction());
        RobotCommand.addInstruction(new ForwardInstruction());
    }

    @Test
    void valid_planet_execution() {
        // given
        String inputStr = "3 5\n1 1 E\nRFRFRFRF\n\n3 2 N\nFRRFLLFFRRFLL\n\n0 3 W\nLLFFFLFLFL";
        String outputStr = "1 1 E\n3 2 N LOST\n2 3 S";

        // then
        assertThatCode(() -> {
            assertThat(Planet.execute(inputStr)).isEqualTo(outputStr);
        }).doesNotThrowAnyException();
    }

}
