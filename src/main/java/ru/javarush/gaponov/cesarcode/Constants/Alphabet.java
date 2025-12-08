package ru.javarush.gaponov.cesarcode.Constants;

import java.util.HashMap;
import java.util.Map;

public class Alphabet {
    public static final char[] ALPHABET = {
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н',
            'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь',
            'э', 'ю', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' ', '[', ']', '{', '}',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    private static final Map<Character, Integer> CHAR_TO_INDEX = new HashMap<>();
    private static final Map<Integer, Character> INDEX_TO_CHAR = new HashMap<>();


    static {
        for (int i = 0; i < ALPHABET.length; i++) {
            char c = ALPHABET[i];
            CHAR_TO_INDEX.put(c, i);
            INDEX_TO_CHAR.put(i, c);
        }
    }

    /**
     * Возвращает индекс символа в алфавите (в нижнем регистре).
     * Если символ не найден — возвращает -1.
     */
    public static int indexOf(char c) {
        return CHAR_TO_INDEX.getOrDefault(Character.toLowerCase(c), -1);
    }

    /**
     * Возвращает символ по заданному индексу.
     */
    public static char get(int index) {
        return INDEX_TO_CHAR.get(index);
    }

    /**
     * Возвращает длину алфавита.
     */
    public static int length() {
        return ALPHABET.length;
    }


}