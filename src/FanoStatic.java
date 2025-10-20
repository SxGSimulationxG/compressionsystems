import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FanoStatic extends CompressionSystem {
    public FanoStatic(char[] inputArray) {
        super(Objects.requireNonNull(inputArray));
    }

    @Override
    protected String getName() {
        return "Shannon-Fano Coding";
    }

    @Override
    protected EncodingResult buildCodebook() {
        Map<Character, String> codes = new HashMap<>();
        if (symbols.length == 0) {
            return new EncodingResult(codes, 0);
        }

        int operations = assignCodes(0, symbols.length, "", codes);
        return new EncodingResult(codes, operations);
    }

    private int assignCodes(int start, int end, String prefix, Map<Character, String> codes) {
        if (end - start == 1) {
            String code = prefix.isEmpty() ? "0" : prefix;
            codes.put(symbols[start], code);
            return 1;
        }

        double totalProbability = 0.0;
        for (int i = start; i < end; i++) {
            totalProbability += probabilities[i];
        }

        double half = totalProbability / 2.0;
        double runningTotal = 0.0;
        int split = start;
        while (split < end - 1) {
            runningTotal += probabilities[split];
            split++;
            if (runningTotal >= half) {
                break;
            }
        }

        int operations = 1; // this division step
        operations += assignCodes(start, split, prefix + '0', codes);
        operations += assignCodes(split, end, prefix + '1', codes);
        return operations;
    }
}
