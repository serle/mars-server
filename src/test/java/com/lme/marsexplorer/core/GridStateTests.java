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
            GridState gridState = new GridState(extentStr);
        }).doesNotThrowAnyException();
    }

    @Test
    void grid_state_to_string() {
        // given
        String extentStr = "3 5";

        // then
        assertThatCode(() -> {
            GridState gridState = new GridState(extentStr);
            assertThat(gridState.toString()).isEqualTo("{\n  Extent: 3 5\n  Smells: [  ]\n}");
        }).doesNotThrowAnyException();
    }


    @Test
    void add_valid_grid_smell() {
        // given
        String extentStr = "5 3";

        // then
        assertThatCode(() -> {
            GridState gridState = new GridState(extentStr);
            gridState.addSmellAt(1, 1);
            gridState.addSmellAt(2, 2);
            assertThat(gridState.toString()).isEqualTo("{\n  Extent: 5 3\n  Smells: [(1 1), (2 2),   ]\n}");
        }).doesNotThrowAnyException();
    }

    @Test
    void add_invalid_grid_smell() {
        // given
        String extentStr = "5 3";

        // then
        assertThatCode(() -> {
            GridState gridState = new GridState(extentStr);
            gridState.addSmellAt(-1, 0);
            gridState.addSmellAt(6, 2);
            assertThat(gridState.toString()).isEqualTo("{\n  Extent: 5 3\n  Smells: [  ]\n}");
        }).doesNotThrowAnyException();
    }
}
