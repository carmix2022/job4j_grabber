package ru.job4j.grabber.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.parse;
import static org.assertj.core.api.Assertions.*;

class HabrCareerDateTimeParserTest {

    @Test
    void whenThen() {
        HabrCareerDateTimeParser parser = new HabrCareerDateTimeParser();
        String input = "2023-05-03T20:27:04+03:00";
        LocalDateTime ref = parse("2023-05-03T20:27:04");
        assertThat(parser.parse(input)).isEqualTo(ref);
    }

}