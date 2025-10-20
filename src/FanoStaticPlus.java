import java.util.Objects;

public class FanoStaticPlus extends CompressionSystem {
    public FanoStaticPlus(char[] inputArray) {
        super(Objects.requireNonNull(inputArray));
    }

    @Override
    protected String getName() {
        return "Shannon-Fano Plus";
    }

    @Override
    protected EncodingResult buildCodebook() {
        throw new UnsupportedOperationException("The enhanced Shannon-Fano variant is not implemented in this comparison tool.");
    }
}
