package com.lme.marsexplorer.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


@SpringBootTest
public class GridExtentTests {

    @Test
    void grid_extent_is_empty() {
        // given
        String extentStr = "";

        // then
        assertThatExceptionOfType(InputParseException.class).isThrownBy(() -> new GridExtent(extentStr))
                .withMessage("%s", "No Grid Extent");
    }

    @Test
    void grid_extent_does_not_contain_two_coordinates() {
        // given
        String extentStr = "4 5 6 7";

        // then
        assertThatExceptionOfType(InputParseException.class).isThrownBy(() -> new GridExtent(extentStr))
                .withMessage("%s", "Grid Extent should contain two integers");
    }

    @Test
    void invalid_x_coordinate() {
        // given
        String extentStr = "E 5";

        // then
        assertThatExceptionOfType(InputParseException.class).isThrownBy(() -> new GridExtent(extentStr))
                .withMessage("%s", "X grid extent coordinate is invalid");
    }

    @Test
    void x_coordinate_greater_than_max() {
        // given
        String extentStr = "53 5";

        // then
        assertThatExceptionOfType(InputParseException.class).isThrownBy(() -> new GridExtent(extentStr))
                .withMessage("%s", "X grid extent coordinate out of range, should be in [0;50]");
    }

    @Test
    void x_coordinate_less_than_min() {
        // given
        String extentStr = "-1 5";

        // then
        assertThatExceptionOfType(InputParseException.class).isThrownBy(() -> new GridExtent(extentStr))
                .withMessage("%s", "X grid extent coordinate out of range, should be in [0;50]");
    }

    @Test
    void invalid_y_coordinate() {
        // given
        String extentStr = "3 E";

        // then
        assertThatExceptionOfType(InputParseException.class).isThrownBy(() -> new GridExtent(extentStr))
                .withMessage("%s", "Y grid extent coordinate is invalid");
    }

    @Test
    void y_coordinate_greater_than_max() {
        // given
        String extentStr = "3 55";

        // then
        assertThatExceptionOfType(InputParseException.class).isThrownBy(() -> new GridExtent(extentStr))
                .withMessage("%s", "Y grid extent coordinate out of range, should be in [0;50]");
    }

    @Test
    void y_coordinate_less_than_min() {
        // given
        String extentStr = "3 -1";

        // then
        assertThatExceptionOfType(InputParseException.class).isThrownBy(() -> new GridExtent(extentStr))
                .withMessage("%s", "Y grid extent coordinate out of range, should be in [0;50]");
    }


    @Test
    void grid_extent_is_valid() {
        // given
        int x = 3;
        int y = 5;
        GridExtent ge = new GridExtent(5, 3);

        //then
        assertThat(ge.isOnGrid(x, y)).isTrue();
    }

    @Test
    void throws_when_position_greater_than_max() {
        // given
        int x = 10;
        int y = 5;
        GridExtent ge = new GridExtent(5, 3);

        //then
        assertThat(ge.isOnGrid(x, y)).isFalse();
    }

    @Test
    void throws_when_position_less_than_min() {
        // given
        int x = 3;
        int y = -5;
        GridExtent ge = new GridExtent(5, 3);

        //then
        assertThat(ge.isOnGrid(x, y)).isFalse();
    }
}
