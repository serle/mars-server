package com.lme.marsexplorer.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.lme.marsexplorer.core.instruction.ForwardInstruction;
import com.lme.marsexplorer.core.instruction.LeftInstruction;
import com.lme.marsexplorer.core.instruction.RightInstruction;

@SpringBootTest
public class InputPacketTests {

    @BeforeTestExecution
    public void setUp() {
        RobotCommand.addInstruction(new LeftInstruction());
        RobotCommand.addInstruction(new RightInstruction());
        RobotCommand.addInstruction(new ForwardInstruction());
    }

    @Test
    void valid_input_packet() {
        // given
        String inputStr = "3 5\n1 1 E\nRFRFRFRF\n\n3 2 N\nFRRFLLFFRRFLL\n\n0 3 W\nLLFFFLFLFL";

        // then
        assertThatCode(() -> {
            InputPacket ip = new InputPacket(inputStr);
            assertThat(ip.toString()).isEqualTo(inputStr);
        }).doesNotThrowAnyException();
    }

    @Test
    void input_packet_is_missing_grid_extent() {
        // given
        String inputStr = "1 1 E\nRFRFRFRF\n\n3 2 N\nFRRFLLFFRRFLL\n\n0 3 W\nLLFFFLFLFL";

        // then
        assertThatExceptionOfType(InputParseException.class).isThrownBy(() -> new InputPacket(inputStr));
    }

    @Test
    void input_packet_is_missing_separator_line() {
        // given
        String inputStr = "3 5\n1 1 E\nRFRFRFRF\n\n3 2 N\nFRRFLLFFRRFLL\n\n0 3 W\nLLFFFLFLFL";

        // then
        assertThatExceptionOfType(InputParseException.class).isThrownBy(() -> new InputPacket(inputStr));
    }

    @Test
    void input_packet_contains_invalid_instruction() {
        // given
        String inputStr = "3 5\n1 1 E\nRFRFRFRF\n\n3 2 N\nFRRFXLFFRRFLL\n\n0 3 W\nLLFFFLFLFL";

        // then
        assertThatExceptionOfType(InputParseException.class).isThrownBy(() -> new InputPacket(inputStr));
    }

}
