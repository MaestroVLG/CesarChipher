package ru.javarush.gaponov.cesarcode.FileManager;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import ru.javarush.gaponov.cesarcode.Exception.FileProcessingException;


public class FileManager {

    // Запрещённые пути (системные директории)
    private static final String[] NOT_USED_DIRS = {
        "C:\\Windows", "C:\\Program Files", "C:\\Program Files (x86)",
                "/etc", "/bin", "/sbin", "/usr", "/boot", "/dev", "/lib"
    };

    public static String readFile(String path) throws FileProcessingException {
        Path filePath = Paths.get(path);

        // Проверяем существование файла
        if (!Files.exists(filePath)) {
            throw new FileProcessingException("Файл не найден!" + path);
        }

        // Провереям что путь не является дирректорией
        if (Files.isDirectory(filePath)) {
            throw new FileProcessingException("Путь указывает на директорию!!!" + path);
        }

        // Проверяем на системные(запрещённые) дирректории
        validateDangerPath(path);

        try {
            return Files.readString(filePath);

        } catch (Exception e){
            throw new FileProcessingException("Ошибка чтения файла!" + path, e);
        }
    }

    public static void writeFile(String path, String content) throws FileProcessingException {
        Path outputPath = Paths.get(path);

        validateDangerPath(path);

        //Проверка на существование родительской дирректории

        Path parentPath = outputPath.getParent();

        if (parentPath != null && !Files.exists(parentPath)) {
            throw new FileProcessingException("Родительская дирректория не существует!" + parentPath);
        }

        try {
            Files.writeString(outputPath, content);
        } catch (Exception e) {
            throw new FileProcessingException("Ошибка записи в файл!" + path, e);
        }
    }
    // Метод для проверки пути на системные дирректории

    private static void validateDangerPath(String path) throws FileProcessingException {

        if (path == null || path.trim().isEmpty()) {
            throw new FileProcessingException("Путь не может быть пустым");
        }

        String cleanPath = path.replace('\\', '/').toLowerCase().trim();

        for(String dir : NOT_USED_DIRS) {
            String cleanDir = dir.replace('\\', '/').toLowerCase().trim();
            if (cleanPath.startsWith(cleanDir)) {
                throw new FileProcessingException("ЗАПРЕЩЁННЫЙ ПУТЬ: запись и чтение из системной дирректории НЕ ДОПУСТИМО!!!" + path);
            }
        }


    }




}
