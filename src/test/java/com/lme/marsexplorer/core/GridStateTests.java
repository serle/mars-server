package com.lme.marsexplorer.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


@SpringBootTest
public class GridStateTests {

    @Test
    void grid_state_constructed() {
        // given
        String extentStr = "3 5";

        assertThatCode(() -> {
            GridState ge = new GridState(extentStr);
        }).doesNotThrowAnyException();
    }

    @Test
    void grid_state_to_string() {
        // given
        String extentStr = "3 5";

        assertThatCode(() -> {
            GridState gs = new GridState(extentStr);
            assertThat(gs.extent.toString()).isEqualTo("3 5");
        }).doesNotThrowAnyException();
    }


    @Test
    void y_coordinate_greater_than_max() {
        // given
        String extentStr = "3 55";

        // then
        assertThatExceptionOfType(InputParseException.class).isThrownBy(() -> new GridExtent(extentStr))
                .withMessage("%s", "Y grid extent coordinate out of range, should be in [0;50]");
    }


}
