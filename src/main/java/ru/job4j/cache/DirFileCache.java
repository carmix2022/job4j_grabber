package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String rsl = null;
        try {
            rsl =  Files.readString(
                    Path.of(String.join("/", cachingDir, key))
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void main(String[] args) {
        DirFileCache dfc = new DirFileCache("./data");
        dfc.get("Names.txt");
        dfc.get("Names.txt");
    }
}