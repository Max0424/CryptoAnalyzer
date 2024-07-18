import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaesarCipher {

    private static final List<Character> RU_ALPHABET_LOWER = List.of(
            'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с',
            'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ',
            'ы', 'ь', 'э', 'ю', 'я'
    );

    private static final List<Character> RU_ALPHABET_UPPER = List.of(
            'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З',
            'И', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С',
            'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ',
            'Ы', 'Ь', 'Э', 'Ю', 'Я'
    );

    private static final List<Character> SYMBOLS = List.of(
            '1','2','3','4','5','6','7','8','9','0',
            ' ',',','.','\'','|',':',';','*','(',')',
            '<','>','@','#','$','%','&','?'
    );

    private static final List<Character> ALPHABET_ALL = createFullAlphabet();
    private static final int ALPHABET_SIZE = ALPHABET_ALL.size();
    private static final Map<Character, Integer> CHAR_TO_INDEX = createCharToIndexMap();

    private static List<Character> createFullAlphabet() {
        List<Character> fullAlphabet = new ArrayList<>();
        fullAlphabet.addAll(RU_ALPHABET_UPPER);
        fullAlphabet.addAll(RU_ALPHABET_LOWER);
        fullAlphabet.addAll(SYMBOLS);
        return fullAlphabet;
    }

    private static Map<Character, Integer> createCharToIndexMap() {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < ALPHABET_ALL.size(); i++) {
            map.put(ALPHABET_ALL.get(i), i);
        }
        return map;
    }

    /**
     * Encrypts the given text using the Caesar cipher with the specified transfer key.
     *
     * @param text     The text to be encrypted.
     * @param shift The number of positions each character in the text should be shifted.
     * @return The encrypted text.
     */
    public static String encrypt(String text, int shift) {
        shift = (shift % ALPHABET_SIZE + ALPHABET_SIZE) % ALPHABET_SIZE; // Ensure transfer is positive
        StringBuilder encrypted = new StringBuilder();

        for (char character : text.toCharArray()) {
            Integer onePosition = CHAR_TO_INDEX.get(character);
            if (onePosition != null) {
                int newPosition = (onePosition + shift) % ALPHABET_SIZE;
                encrypted.append(ALPHABET_ALL.get(newPosition));
            } else {
                encrypted.append(character); // Keep non-alphabetic characters unchanged
            }
        }
        return encrypted.toString();
    }
    public static String decrypt(String text, int transfer) {
        return encrypt(text, -transfer); // Decrypt is just inverse of encrypt with negative transfer
    }
}
