public class HuffmanStatic extends CompressionSystem {
    public HuffmanStatic(char[] inputArray) {
        input = inputArray;
        Object[] analysis = analyzeCharacters(input);
        sequence = (char[]) analysis[0];
        frequency = (int[]) analysis[1];
    }

    @Override
    public int getProcesses() {
        return processes;
    }

    @Override
    public char[] getEncodingScheme() {
        return encodingScheme;
    }
}
