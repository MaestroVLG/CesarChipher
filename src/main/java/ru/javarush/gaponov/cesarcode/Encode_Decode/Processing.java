package ru.javarush.gaponov.cesarcode.Encode_Decode;

import ru.javarush.gaponov.cesarcode.Constants.Alphabet;

public class Processing {

    /** Метод для шифрования строки с сохранением регистра */
    public static String encrypt(String str, int key) {
        StringBuilder result = new StringBuilder();

        for (char c : str.toCharArray()) {
            // Проверяем, является ли символ буквой (для сохранения регистра)
            boolean isUpperCase = Character.isUpperCase(c);
            char lowerChar = Character.toLowerCase(c);

            int index = Alphabet.indexOf(lowerChar);

            if (index == -1) {
                // Если символ не найден в алфавите — оставляем как есть
                result.append(c);
            } else {
                // Вычисляем новый индекс с учётом сдвига
                int newIndex = (index + key) % Alphabet.length();
                if (newIndex < 0) {
                    newIndex += Alphabet.length();
                }

                // Получаем символ из алфавита (всегда строчный)
                char decryptedChar = Alphabet.get(newIndex);

                // Сохраняем исходный регистр
                if (isUpperCase) {
                    result.append(Character.toUpperCase(decryptedChar));
                } else {
                    result.append(decryptedChar);
                }
            }
        }
        return result.toString();
    }

    /** Метод для расшифрования строки */
    public static String decrypt(String str, int key) {
        return encrypt(str, -key);
    }
}