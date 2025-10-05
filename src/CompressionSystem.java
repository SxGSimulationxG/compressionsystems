public abstract class CompressionSystem {
    char[] input;
    char[] sequence;
    double[] prob;
    char[] encodingScheme;
    int processes;

    public abstract int getProcesses();
    public abstract char[] getEncodingScheme();
}
