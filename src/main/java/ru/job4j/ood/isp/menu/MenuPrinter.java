package ru.job4j.ood.isp.menu;

public interface MenuPrinter {

    void print(Menu menu);

    default String repeat(int n, String value) {
        return new String(new char[n]).replace("\0", value);
    }
}