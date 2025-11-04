import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

public class HuffmanStatic extends CompressionSystem {
    public HuffmanStatic(char[] inputArray) {
        super(Objects.requireNonNull(inputArray));
    }

    @Override
    protected String getName() {
        return "Huffman Coding";
    }

    @Override
    protected EncodingResult buildCodebook() {
        Map<Character, String> codes = new HashMap<>();
        if (symbols.length == 0) {
            return new EncodingResult(codes, 0);
        }

        // Build the priority queue containing one leaf node per symbol ordered by frequency
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.frequency));
        for (int i = 0; i < symbols.length; i++) {
            queue.offer(new Node(symbols[i], frequencies[i]));
        }

        int operations = symbols.length; // queue insertions

        // Repeatedly merge the lowest-frequency nodes to construct the Huffman tree
        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();
            Node parent = new Node('\0', left.frequency + right.frequency, left, right);
            queue.offer(parent);
            operations += 3; // two polls and one offer
        }

        Node root = queue.poll();
        if (root.isLeaf()) {
            // Special case for single-symbol inputs where the code must still have at least one bit
            codes.put(root.symbol, "0");
            operations++;
        } else {
            // Traverse the tree to assign bit strings to each symbol
            operations += assignCodes(root, "", codes);
        }

        return new EncodingResult(codes, operations);
    }

    private int assignCodes(Node node, String prefix, Map<Character, String> codes) {
        if (node.isLeaf()) {
            String code = prefix.isEmpty() ? "0" : prefix;
            codes.put(node.symbol, code);
            return 1;
        }

        // Recursively walk left and right subtrees to build the final codewords
        int operations = 1; // split operation
        operations += assignCodes(Objects.requireNonNull(node.left), prefix + '0', codes);
        operations += assignCodes(Objects.requireNonNull(node.right), prefix + '1', codes);
        return operations;
    }

    private static class Node {
        final char symbol;
        final int frequency;
        final Node left;
        final Node right;

        Node(char symbol, int frequency) {
            this(symbol, frequency, null, null);
        }

        Node(char symbol, int frequency, Node left, Node right) {
            this.symbol = symbol;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        boolean isLeaf() {
            return left == null && right == null;
        }
    }
}
