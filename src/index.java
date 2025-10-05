import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class index {
    public static void main(String[] args) {
        String filePath = "input.txt";

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            char[] charArray = content.toCharArray();
        }
        catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}