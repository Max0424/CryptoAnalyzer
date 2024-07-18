import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    public static void processFile(String inputFile, String outputFile, int encryptionKey, boolean isEncrypt) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputFile));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFile))) {

            String str;
            while ((str = reader.readLine()) != null) {
                String processedStr;
                if (isEncrypt) {
                    processedStr = CaesarCipher.encrypt(str, encryptionKey);
                } else {
                    processedStr = CaesarCipher.decrypt(str, encryptionKey);
                }
                writer.write(processedStr);
                writer.newLine();
            }
        }
    }
}
