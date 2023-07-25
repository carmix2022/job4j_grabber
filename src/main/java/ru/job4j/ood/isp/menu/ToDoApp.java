package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class ToDoApp {
    public static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");


    public static final Integer ADD_ELEMENT_TO_ROOT = 1;
    public static final Integer ADD_ELEMENT_TO_PARENT = 2;
    public static final Integer ACTION_RUN = 3;
    public static final Integer SHOW_MENU = 4;

    public static final String SELECT = "Выберите меню";
    public static final String PARENT = "Введите название родительской задачи";
    public static final String TASK_TEXT = "Введите название Вашей задачи";
    public static final String EXIT = "Конец работы";

    public static final String MENU = """
                Введите 1, чтобы добавить элемент в корень меню.
                Введите 2, чтобы добавить элемент к родительскому элементу.
                Введите 3, чтобы вызвать действие, привязанное к пункту меню.
                Введите 4, чтобы вывести меню в консоль.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new SimpleMenu();
        MenuPrinter menuPrinter = new MenuConsolePrinter();
        start(menu, menuPrinter, scanner);
    }

    private static void start(Menu menu, MenuPrinter menuPrinter, Scanner scanner) {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (ADD_ELEMENT_TO_ROOT == userChoice) {
                System.out.println(TASK_TEXT);
                String text = scanner.nextLine();
                menu.add(Menu.ROOT, text, DEFAULT_ACTION);
            } else if (ADD_ELEMENT_TO_PARENT == userChoice) {
                System.out.println(PARENT);
                String parent = scanner.nextLine();
                System.out.println(TASK_TEXT);
                String text = scanner.nextLine();
                menu.add(parent, text, DEFAULT_ACTION);
            } else if (ACTION_RUN == userChoice) {
                System.out.println(TASK_TEXT);
                String text = scanner.nextLine();
                menu.select(text).ifPresentOrElse(menuItemInfo -> menuItemInfo.getActionDelegate().delegate(),
                        () -> System.out.println("задача не найдена"));
            } else if (SHOW_MENU == userChoice) {
                menuPrinter.print(menu);
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }
}
