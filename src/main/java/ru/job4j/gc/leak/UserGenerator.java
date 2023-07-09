package ru.job4j.gc.leak;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {

    public static final String PATH_NAMES = "src/main/java/ru/job4j/gc/leak/files/names.txt";
    public static final String PATH_SURNAMES = "src/main/java/ru/job4j/gc/leak/files/surnames.txt";
    public static final String PATH_PATRONS = "src/main/java/ru/job4j/gc/leak/files/patr.txt";

    public static final String SEPARATOR = " ";
    public static final Integer NEW_USERS = 1000;

    public static List<String> names;
    public static List<String> surnames;
    public static List<String> patrons;
    private static List<User> users = new ArrayList<>();
    private Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    @Override
    public void generate() {
        users.clear();
        StringBuilder userName = new StringBuilder();
        for (int i = 0; i < NEW_USERS; i++) {
            userName.append(surnames.get(random.nextInt(surnames.size()))).append(SEPARATOR)
                    .append(names.get(random.nextInt(names.size()))).append(SEPARATOR)
                    .append(patrons.get(random.nextInt(patrons.size())));
            users.add(new User(userName.toString()));
            userName.setLength(0);

        }
    }

    private void readAll() {
            names = read(PATH_NAMES);
            surnames = read(PATH_SURNAMES);
            patrons = read(PATH_PATRONS);
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }

    public static List<User> getUsers() {
        return users;
    }
}