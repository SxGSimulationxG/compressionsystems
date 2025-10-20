import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ShannonStatic extends CompressionSystem {
    public ShannonStatic(char[] inputArray) {
        super(Objects.requireNonNull(inputArray));
    }

    @Override
    protected String getName() {
        return "Shannon Coding";
    }

    @Override
    protected EncodingResult buildCodebook() {
        Map<Character, String> codes = new HashMap<>();
        if (symbols.length == 0) {
            return new EncodingResult(codes, 0);
        }

        double cumulative = 0.0;
        int operations = 0;
        for (int i = 0; i < symbols.length; i++) {
            double probability = probabilities[i];
            int length = probability == 0.0 ? 1 : (int) Math.ceil(-log2(probability));
            if (length == 0) {
                length = 1;
            }

            String code = binaryFraction(cumulative, length);
            codes.put(symbols[i], code);
            cumulative += probability;
            operations += 1 + length; // one assignment plus bit generation steps
        }

        if (symbols.length == 1) {
            codes.put(symbols[0], "0");
        }

        return new EncodingResult(codes, operations);
    }

    private double log2(double value) {
        return Math.log(value) / Math.log(2.0);
    }

    private String binaryFraction(double value, int length) {
        StringBuilder builder = new StringBuilder();
        double fractional = value;
        for (int i = 0; i < length; i++) {
            fractional *= 2.0;
            if (fractional >= 1.0) {
                builder.append('1');
                fractional -= 1.0;
            } else {
                builder.append('0');
            }
        }

        if (builder.length() == 0) {
            builder.append('0');
        }
        return builder.toString();
    }
}
