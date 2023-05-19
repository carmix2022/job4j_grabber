package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator extends DirFileCache {
    public Emulator(String cachingDir) {
        super(cachingDir);
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Укажите кэшируемую директорию:");
        String dirName = input.nextLine();
        Emulator emulatorDFC = new Emulator(dirName);
        boolean programFlag = true;
        while (programFlag) {
            System.out.println("Введите названия файла c расширением или цифру 3 для выхода из программы:");
            String fileName = input.nextLine();
            if (fileName.equals("3")) {
                programFlag = false;
                break;
            }
            System.out.println("Вы можете добавить содержимое файла в кэш (введите цифру 1) "
                    + "или получить содержимое файла из кэша (введите цифру 2). Если хотите сменить файл, введите 0");
            boolean inputFlag = true;
            while (inputFlag) {
                int inputNumber = Integer.parseInt(input.nextLine());
                switch (inputNumber) {
                    case 0 -> {
                        inputFlag = false;
                    }
                    case 1 -> {
                        emulatorDFC.put(fileName, emulatorDFC.load(fileName));
                        System.out.println("успешно");
                    }
                    case 2 -> {
                        System.out.println(emulatorDFC.get(fileName));
                    }
                    case 3 -> {
                        inputFlag = false;
                        programFlag = false;
                    }
                    default -> {
                        System.out.println("некорректная цифра, попробуйте еще");
                    }
                }
            }
        }
    }
}
