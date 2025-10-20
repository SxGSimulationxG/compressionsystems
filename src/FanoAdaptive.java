import java.util.Objects;

public class FanoAdaptive extends CompressionSystem {
    public FanoAdaptive(char[] inputArray) {
        super(Objects.requireNonNull(inputArray));
    }

    @Override
    protected String getName() {
        return "Shannon-Fano Adaptive";
    }

    @Override
    protected EncodingResult buildCodebook() {
        throw new UnsupportedOperationException("Adaptive Shannon-Fano coding is not implemented in this comparison tool.");
    }
}
