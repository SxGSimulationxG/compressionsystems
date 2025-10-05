import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Index {
    static char[] charArray;
    public static void main(String[] args) {
        String filePath = "input.txt";

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            charArray = content.toCharArray();
        }
        catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        HuffmanStatic huffmanStatic = new HuffmanStatic(charArray);

        HuffmanAdaptive huffmanAdaptive = new HuffmanAdaptive(charArray);

        ShannonStatic shannonStatic = new ShannonStatic(charArray);

        ShannonAdaptive shannonAdaptive = new ShannonAdaptive(charArray);

        FanoStatic fanoStatic = new FanoStatic(charArray);

        FanoAdaptive fanoAdaptive = new FanoAdaptive(charArray);

        FanoStaticPlus fanoStaticPlus = new FanoStaticPlus(charArray);


    }
}