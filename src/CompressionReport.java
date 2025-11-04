import java.util.Map;

public class CompressionReport {
    private final String algorithmName;
    private final int totalBits;
    private final int originalBits;
    private final int processes;
    private final Map<Character, String> codebook;
    private final double entropy;
    private final double averageCodewordLength;

    public CompressionReport(String algorithmName, int totalBits, int originalBits, int processes,
                             Map<Character, String> codebook, double entropy, double averageCodewordLength) {
        this.algorithmName = algorithmName;
        this.totalBits = totalBits;
        this.originalBits = originalBits;
        this.processes = processes;
        this.codebook = Map.copyOf(codebook);
        this.entropy = entropy;
        this.averageCodewordLength = averageCodewordLength;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public int getTotalBits() {
        return totalBits;
    }

    public int getOriginalBits() {
        return originalBits;
    }

    public int getProcesses() {
        return processes;
    }

    public Map<Character, String> getCodebook() {
        return codebook;
    }

    public double getEntropy() {
        return entropy;
    }

    public double getAverageCodewordLength() {
        return averageCodewordLength;
    }

    public int getTotalBytesRounded() {
        return (int) Math.ceil(totalBits / 8.0);
    }

    public double compressionRatio() {
        if (originalBits == 0) {
            return 0.0;
        }
        return (double) totalBits / (double) originalBits;
    }
}
