package com.lme.marsexplorer.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


@SpringBootTest
public class OrientationTests {

    @Test
    void valid_orientation() {
        // given
        String orientationStr = "N";

        //then
        assertThatCode(() -> {
            Orientation o = Orientation.toOrientation(orientationStr);
            assertThat(o).isEqualTo(Orientation.N);
        }).doesNotThrowAnyException();
    }

    @Test
    void invalid_orientation() {
        // given
        String orientationStr = "F";

        // then
        assertThatExceptionOfType(InputParseException.class).isThrownBy(() -> Orientation.toOrientation(orientationStr));
    }

}
