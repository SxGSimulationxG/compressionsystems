import java.util.LinkedHashMap;
import java.util.Map;

public abstract class CompressionSystem {
    char[] input;
    char[] sequence;
    int[] frequency;
    double[] prob;
    char[] encodingScheme;
    double avgLength;
    int processes;

    public abstract int getProcesses();
    public abstract char[] getEncodingScheme();

    public Object[] analyzeCharacters(char[] input) {
        Map<Character, Integer> frequencyMap = new LinkedHashMap<>();

        for (char c : input) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        char[] uniqueChars = new char[frequencyMap.size()];
        int[] frequencies = new int[frequencyMap.size()];

        int i = 0;
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            uniqueChars[i] = entry.getKey();
            frequencies[i] = entry.getValue();
            i++;
        }

        return new Object[]{uniqueChars, frequencies};
    }
}
