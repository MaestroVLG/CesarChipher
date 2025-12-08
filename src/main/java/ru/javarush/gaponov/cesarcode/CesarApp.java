package ru.javarush.gaponov.cesarcode;


import ru.javarush.gaponov.cesarcode.Constants.Commands;
import ru.javarush.gaponov.cesarcode.Encode_Decode.Processing;
import ru.javarush.gaponov.cesarcode.Exception.FileProcessingException;
import ru.javarush.gaponov.cesarcode.FileManager.FileManager;

import java.util.Scanner;

import static java.lang.System.out;

public class CesarApp {

    private static final String CYAN = "\u001B[36m";
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\u001B[0m";

    private static void printCyan(String message) {
        out.println(CYAN + message + RESET);
    }

    private static void printRed(String message) {
        out.println(RED + message + RESET);
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        printCyan("""
                ===============
                =             =
                ==Шифр Цезаря==
                =             =
                ==VERSION 1.0==
                =             =
                ===============
                """);

        System.out.println("Доступные команды: Зашифровать(encode), " +
                "Расшифровать(decode), Завершить работу(exit)");

        while (true) {
            System.out.print("Введите команду: ");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals(Commands.EXIT)) {
                printCyan("Спасибо за использование программы!\n\n До свидания!");
                break;

            }

            try {
                if (command.equals(Commands.ENCODE)) {
                    processEncode(scanner);
                } else if (command.equals(Commands.DECODE)) {
                    processDecode(scanner);
                } else {
                    printRed("Неизвестная команда. Попробуйте снова.");

                }
            } catch (FileProcessingException e) {
                printRed("ОШИБКА! - " + e.getMessage());
            }
        }

        scanner.close();
    }

    private static void processEncode(Scanner scanner) {

        try {
            out.println("Введите путь к файлу для шифрования: ");
            String inputPath = scanner.nextLine().trim();
            validateFile(inputPath);

            out.println("Введите путь к файлу для записи результата: ");
            String outputPath = scanner.nextLine().trim();
            validateFile(outputPath);

            out.println("Введите ключ для шифрования: ");
            int key = Integer.parseInt(scanner.nextLine().trim());

            String content = FileManager.readFile(inputPath);
            String encryptedContent = Processing.encrypt(content, key);
            FileManager.writeFile(outputPath, encryptedContent);

            printCyan("Шифрование завершено успешно! Результат сохранен в файл: " + outputPath);

        } catch (NumberFormatException e) {
            printRed("ОШИБКА! - Ключ должен быть целым числом.");
        } catch (FileProcessingException e) {
            printRed("Ошибка обработки файла: " + e.getMessage());
        }

    }

    private static void processDecode(Scanner scanner) {
        try {
            out.println("Введите путь к файлу для дешифрования: ");
            String inputPath = scanner.nextLine().trim();
            validateFile(inputPath);

            out.println("Введите путь к файлу для записи результата: ");
            String outputPath = scanner.nextLine().trim();
            validateFile(outputPath);

            out.println("Введите ключ для дешифрования: ");
            int key = Integer.parseInt(scanner.nextLine().trim());
            out.println("Ждите...");

            String content = FileManager.readFile(inputPath);
            String decrypted = Processing.decrypt(content, key);
            FileManager.writeFile(outputPath, decrypted);

            printCyan("Дешифрование завершено успешно! Результат сохранен в файл: " + outputPath);

        } catch (NumberFormatException e) {
            printRed("ОШИБКА! - Ключ должен быть целым числом.");
        } catch (FileProcessingException e) {
            printRed("Ошибка обработки файла: " + e.getMessage());
        }
    }

    private static void validateFile(String path) throws FileProcessingException {
        if (!path.toLowerCase().trim().endsWith(".txt")) {
            throw new FileProcessingException("Допустимы только файлы с расширением .txt " + path);
        }
    }
}


