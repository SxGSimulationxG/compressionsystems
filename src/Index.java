import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Index {
    public static void main(String[] args) {
        String filePath = args.length > 0 ? args[0] : "src/input.txt";
        Path path = Paths.get(filePath);

        char[] input;
        try {
            String content = Files.readString(path);
            input = content.toCharArray();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        if (input.length == 0) {
            System.out.println("The provided file is empty. There is no data to compress.");
            return;
        }

        CompressionSystem[] systems = new CompressionSystem[]{
            new HuffmanStatic(input),
            new ShannonStatic(input),
            new FanoStatic(input)
        };

        int originalBits = input.length * 8;
        System.out.println("Original size: " + originalBits + " bits (" + (int) Math.ceil(originalBits / 8.0) + " bytes)\n");

        for (CompressionSystem system : systems) {
            CompressionReport report = system.generateReport();
            System.out.println(report.getAlgorithmName());
            System.out.println("Compressed size: " + report.getTotalBits() + " bits (" + report.getTotalBytesRounded() + " bytes)");
            System.out.println("Processes executed: " + report.getProcesses());
            double ratio = report.compressionRatio(originalBits);
            System.out.printf("Compression ratio: %.4f\n", ratio);
            System.out.println();
        }
    }
}
