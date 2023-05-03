package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import static java.time.format.DateTimeFormatter.*;

public class HabrCareerDateTimeParser implements DateTimeParser {
    @Override
    public LocalDateTime parse(String parse) {
        return LocalDateTime.parse(
                LocalDateTime.parse(parse, ISO_OFFSET_DATE_TIME).toString()
        );
    }
}
