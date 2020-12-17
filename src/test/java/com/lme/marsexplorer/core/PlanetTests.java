package com.lme.marsexplorer.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PlanetTests {

    @Test
    void valid_planet_execution() {
        // given
        Planet.addInstructions();
        String inputStr = "3 5\n1 1 E\nRFRFRFRF\n\n3 2 N\nFRRFLLFFRRFLL\n\n0 3 W\nLLFFFLFLFL";
        String outputStr = "1 1 E\n3 2 N LOST\n2 3 S";

        // then
        assertThatCode(() -> assertThat(Planet.execute(inputStr)).isEqualTo(outputStr)).doesNotThrowAnyException();
    }

}
