package com.lme.marsexplorer.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


@SpringBootTest
public class PositionTest {

    @Test
    void create_valid_position() {
        // given
        int x = 3;
        int y = 5;
        GridExtent ge = new GridExtent(3, 5);

        //then
        assertThatCode(() -> {
            Position p = new Position(x, y, ge);
            assertThat(p.getX()).isEqualTo(3);
            assertThat(p.getY()).isEqualTo(5);
        }).doesNotThrowAnyException();
    }

    @Test
    void throws_when_position_greater_than_max() {
        // given
        int x = 10;
        int y = 5;
        GridExtent ge = new GridExtent(3, 5);

        //then
        assertThatExceptionOfType(InvalidPositionException.class).isThrownBy(() -> new Position(x, y, ge))
                .withMessage("%s", "X coordinate out of bounds");

    }

    @Test
    void throws_when_position_less_than_min() {
        // given
        int x = 3;
        int y = -5;
        GridExtent ge = new GridExtent(3, 5);

        //then
        assertThatExceptionOfType(InvalidPositionException.class).isThrownBy(() -> new Position(x, y, ge))
                .withMessage("%s", "Y coordinate out of bounds");

    }
}
