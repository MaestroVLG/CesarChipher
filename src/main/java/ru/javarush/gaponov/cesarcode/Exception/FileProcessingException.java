package ru.javarush.gaponov.cesarcode.Exception;

public class FileProcessingException extends RuntimeException {

    private static final String RED = "\u001B[31m";
    public FileProcessingException(String message) {
        super(RED + message);
    }
    public FileProcessingException(String message, Throwable cause) {
        super(RED + message, cause);
    }
}
