package ru.job4j.gc.leak;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public interface Generate  {

    void generate();

    default List<String> read(String path) {
        try (Stream<String> stream = Files.lines(Path.of(path))) {
            return stream.toList();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

    }
}