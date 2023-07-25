package ru.job4j.ood.isp.menu;

public class MenuStringPrinter implements MenuPrinter {
    private final StringBuilder buffer = new StringBuilder();
    @Override
    public void print(Menu menu) {
        menu.forEach(i -> buffer.append(repeat(i.getNumber().length() - 2, "-"))
                .append(i.getNumber())
                .append(i.getName())
                .append(System.lineSeparator()));
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}
