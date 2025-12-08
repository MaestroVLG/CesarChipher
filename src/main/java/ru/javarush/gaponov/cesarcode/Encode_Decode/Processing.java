package ru.javarush.gaponov.cesarcode.Encode_Decode;

import ru.javarush.gaponov.cesarcode.Constants.Alphabet;

public class Processing {

    /** Медод для шифрования строки */

    public static String encrypt(String str, int key){

        StringBuilder result = new StringBuilder();

        for (char c : str.toCharArray()){
           int index = Alphabet.indexOf(c);

            if (index == -1) {
                result.append(c);  // если символ не найден в алфавите,то оставляем как есть

            } else {  // вычисляем индекс нового символа с учётом сдвига(ключа = key)
                int newIndex = (index + key) % Alphabet.length();
                if (newIndex < 0) {
                    newIndex += Alphabet.length(); // если индекс отрицательный, то добавляем длину алфавита

                }

                result.append(Alphabet.get(newIndex)); // добавляем зашифрованный символ в результат
            }

        }
        return result.toString(); //возврат итоговой строки

    }
    /** Медод для расшифрования строки
     * Чтобы не дублировать код, используем уже созданный метод encrypt, в котором заложен алгоритм расшифровки
     * */

    public static String decrypt(String str, int key){
        return encrypt(str, -key);

    }


}
