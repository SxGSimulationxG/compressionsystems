import java.util.Objects;

public class ShannonAdaptive extends CompressionSystem {
    public ShannonAdaptive(char[] inputArray) {
        super(Objects.requireNonNull(inputArray));
    }

    @Override
    protected String getName() {
        return "Shannon Adaptive";
    }

    @Override
    protected EncodingResult buildCodebook() {
        throw new UnsupportedOperationException("Adaptive Shannon coding is not implemented in this comparison tool.");
    }
}
