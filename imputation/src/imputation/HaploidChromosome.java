package imputation;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

public record HaploidChromosome(Gene[] sequence) {
    public HaploidChromosome(String code) {
        this(parse(code));
    }

    private static Gene[] parse(String code) {
        int segmentSize = Gene.BASES;
        int segmentNumber = code.length() / segmentSize;

        Gene[] result = new Gene[segmentNumber];
        String segment;
        for(int seg = 0; seg < segmentNumber; seg++) {
            segment = code.substring(seg * segmentSize, seg * segmentSize + segmentSize);
            result[seg] = new Gene(segment);
        }

        return result;
    }

    /**
     * @return The number of genes in the sequence
     */
    public int length() {
        return sequence.length;
    }

    public Gene position(int pos) {
        return sequence[pos];
    }

    public static HaploidChromosome random(int len) {
        return new HaploidChromosome(Stream.generate(Gene::randomGene)
                .limit(len)
                .toArray(Gene[]::new));
    }

    @Override
    public int hashCode() {
        return stream(sequence)
                .map(Gene::hashCode)
                .reduce(1, (a, b) -> a * b);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof HaploidChromosome other
                && Arrays.equals(this.sequence, other.sequence);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("|");

        stream(sequence).map(Objects::toString).forEach(joiner::add);

        return "(" + sequence.length + "): " + joiner;
    }
}
