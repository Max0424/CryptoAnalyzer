import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                int choice = Menu(scanner);

                switch (choice) {
                    case 1:
                        encryptData(scanner);
                        break;

                    case 2:
                        decryptData(scanner);
                        break;

                    case 3:
                        System.out.println("Завершение программы");
                        return;

                }
            }
        }
    }

    private static int Menu(Scanner scanner) {
        while (true) {
            try {
                System.out.println("Выберите режим работы: ");
                System.out.println("1. Шифрование текста");
                System.out.println("2. Расшифрование текста");
                System.out.println("3. Выход");
                int choice = scanner.nextInt();

                if (choice >= 1 && choice <= 3) {
                    return choice;
                } else {
                    System.out.println("Неверный ввод. Пожалуйста, выберите 1, 2 или 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Неверный ввод. Пожалуйста, введите целое число.");
                scanner.next();
            }
        }
    }

    private static void encryptData(Scanner scanner) {
        scanner.nextLine();

        System.out.println("Введите путь к файлу: ");
        String inputFileEncryption = scanner.nextLine();
        System.out.println("Введите путь к файлу для зашифрованного текста: ");
        String outputFileEncryption = scanner.nextLine();
        System.out.println("Введите ключ для сдвига: ");
        int keyEncryption = scanner.nextInt();
        scanner.nextLine();

        if (!isValidInput(inputFileEncryption, keyEncryption)) {
            return;
        }

        try {
            FileReader.processFile(inputFileEncryption, outputFileEncryption, keyEncryption, true);
            System.out.printf("Текст зашифрован и записан в %s%n", outputFileEncryption);
        } catch (IOException e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }

    private static void decryptData(Scanner scanner) {
        scanner.nextLine();

        System.out.println("Введите путь к файлу с зашифрованным текстом: ");
        String inputFileDecryption = scanner.nextLine();
        System.out.println("Введите путь к файлу для расшифровки текста: ");
        String outputFileDecryption = scanner.nextLine();
        System.out.println("Введите ключ (сдвиг):");
        int keyDecryption = scanner.nextInt();
        scanner.nextLine();

        if (!isValidInput(inputFileDecryption, keyDecryption)) {
            return;
        }

        try {
            FileReader.processFile(inputFileDecryption, outputFileDecryption, keyDecryption, false);
            System.out.printf("Текст успешно расшифрован и записан в %s%n", outputFileDecryption);
        } catch (IOException e) {
            System.out.println("Произошла ошибка при обработке файлов: " + e.getMessage());
        }
    }

    private static boolean isValidInput(String filePath, int key) {
        if (!fileExists(filePath)) {
            System.out.println("Файл не существует.");
            return false;
        }

        if (!isKeyValid(key)) {
            System.out.println("Ключ должен быть в пределах от 0 до 31");
            return false;
        }

        return true;
    }

    public static boolean fileExists(String filePath) {
        File file = new File(filePath);
        return file.exists() && !file.isDirectory();
    }

    public static boolean isKeyValid(int key) {
        return key >= 0 && key < 32;
    }
}
