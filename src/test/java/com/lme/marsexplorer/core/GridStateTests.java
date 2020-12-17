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

        // then
        assertThatCode(() -> {
            GridState ge = new GridState(extentStr);
        }).doesNotThrowAnyException();
    }

    @Test
    void grid_state_to_string() {
        // given
        String extentStr = "3 5";

        // then
        assertThatCode(() -> {
            GridState gs = new GridState(extentStr);
            assertThat(gs.toString()).isEqualTo("{\n  Extent: 3 5\n  Smells: [  ]\n}");
        }).doesNotThrowAnyException();
    }
}
