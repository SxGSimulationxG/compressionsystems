import java.util.*;

public abstract class CompressionSystem {
    protected final char[] input;
    protected final char[] symbols;
    protected final int[] frequencies;
    protected final double[] probabilities;

    protected CompressionSystem(char[] inputArray) {
        this.input = Objects.requireNonNull(inputArray, "Input array cannot be null");
        Object[] analysis = analyzeCharacters(inputArray);
        this.symbols = (char[]) analysis[0];
        this.frequencies = (int[]) analysis[1];
        this.probabilities = computeProbabilities(frequencies, inputArray.length);
    }

    public CompressionReport generateReport() {
        EncodingResult result = buildCodebook();
        Map<Character, String> codebook = result.codebook();
        int encodedBits = 0;

        for (char c : input) {
            String code = codebook.get(c);
            if (code == null) {
                throw new IllegalStateException("Missing code for character: '" + c + "'");
            }
            encodedBits += code.length();
        }

        double entropy = computeEntropy();
        double averageCodeLength = computeAverageCodeLength(codebook);
        int totalProcesses = result.processes() + input.length; // include encoding steps
        return new CompressionReport(getName(), encodedBits, totalProcesses, codebook, entropy, averageCodeLength);
    }

    protected abstract String getName();

    protected abstract EncodingResult buildCodebook();

    protected Object[] analyzeCharacters(char[] input) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char c : input) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        List<Map.Entry<Character, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());

        entries.sort((a, b) -> {
            int freqCompare = Integer.compare(b.getValue(), a.getValue());
            return (freqCompare != 0) ? freqCompare : Character.compare(a.getKey(), b.getKey());
        });

        char[] uniqueChars = new char[entries.size()];
        int[] frequencies = new int[entries.size()];

        int i = 0;
        for (Map.Entry<Character, Integer> entry : entries) {
            uniqueChars[i] = entry.getKey();
            frequencies[i] = entry.getValue();
            i++;
        }

        return new Object[]{uniqueChars, frequencies};
    }

    private double[] computeProbabilities(int[] frequencies, int totalLength) {
        double[] probs = new double[frequencies.length];
        for (int i = 0; i < frequencies.length; i++) {
            probs[i] = (double) frequencies[i] / (double) totalLength;
        }
        return probs;
    }

    private double computeEntropy() {
        double entropy = 0.0;
        for (double probability : probabilities) {
            if (probability > 0.0) {
                entropy -= probability * (Math.log(probability) / Math.log(2.0));
            }
        }
        return entropy;
    }

    private double computeAverageCodeLength(Map<Character, String> codebook) {
        double averageLength = 0.0;
        for (int i = 0; i < symbols.length; i++) {
            char symbol = symbols[i];
            String code = codebook.get(symbol);
            if (code == null) {
                throw new IllegalStateException("Missing code for character: '" + symbol + "'");
            }
            averageLength += probabilities[i] * code.length();
        }
        return averageLength;
    }

    protected record EncodingResult(Map<Character, String> codebook, int processes) { }
}
