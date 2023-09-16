package imputation;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

public class HaploidChromosome {
    final Gene[] sequence;
    public HaploidChromosome(Gene[] sequence) throws IllegalArgumentException {
        this.sequence = sequence;
    }

    public static HaploidChromosome random(int len) {
        return new HaploidChromosome(Stream.generate(Gene::randomGene)
                .limit(len)
                .toArray(Gene[]::new));
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("|");

        stream(sequence).map(Objects::toString).forEach(joiner::add);

        return "(" + sequence.length + "): " + joiner.toString();
    }
}
