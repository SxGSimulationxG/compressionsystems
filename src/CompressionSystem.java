import java.util.*;

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
        Map<Character, Integer> frequencyMap = new HashMap<>();

        // Count how often each character appears
        for (char c : input) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Convert to a sortable list
        List<Map.Entry<Character, Integer>> entries = new ArrayList<>(frequencyMap.entrySet());

        // Sort by frequency (descending), then alphabetically
        entries.sort((a, b) -> {
            int freqCompare = Integer.compare(b.getValue(), a.getValue());
            return (freqCompare != 0) ? freqCompare : Character.compare(a.getKey(), b.getKey());
        });

        // Build result arrays
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
}
