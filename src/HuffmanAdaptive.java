import java.util.Objects;

public class HuffmanAdaptive extends CompressionSystem {
    public HuffmanAdaptive(char[] inputArray) {
        super(Objects.requireNonNull(inputArray));
    }

    @Override
    protected String getName() {
        return "Huffman Adaptive";
    }

    @Override
    protected EncodingResult buildCodebook() {
        throw new UnsupportedOperationException("Adaptive Huffman coding is not implemented in this comparison tool.");
    }
}
