package com.lme.marsexplorer.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


@SpringBootTest
public class PositionTests {

    @Test
    void parse_valid_position_string() {
        // given
        String positionStr = "1 1";
        GridExtent ge = new GridExtent(3, 5);

        //then
        assertThatCode(() -> {
            Position p = new Position(positionStr, ge);
            assertThat(p.getX()).isEqualTo(1);
            assertThat(p.getY()).isEqualTo(1);
        }).doesNotThrowAnyException();
    }

    @Test
    void parse_invalid_position_string() {
        // given
        String positionStr = "-1 10";
        GridExtent ge = new GridExtent(3, 5);

        //then
        assertThatExceptionOfType(InputParseException.class).isThrownBy(() -> new Position(positionStr, ge));
    }

    @Test
    void create_valid_position() {
        // given
        int x = 5;
        int y = 3;

        //then
        assertThatCode(() -> {
            Position p = new Position(x, y);
            assertThat(p.getX()).isEqualTo(x);
            assertThat(p.getY()).isEqualTo(y);
        }).doesNotThrowAnyException();
    }

}
