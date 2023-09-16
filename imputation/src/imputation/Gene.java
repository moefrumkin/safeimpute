package imputation;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

public class Gene{
    public static final int BASES = 4;

    final Base[] code;

    public Gene(Base[] code) throws IllegalArgumentException {
        if(code.length != Gene.BASES)
            throw new IllegalArgumentException(
                    "Wrong number of bases. Expected " + Gene.BASES + ", given " + code.length);

        this.code = code;
    }

    public Gene(String code) throws IllegalArgumentException {
        if(code.length() != Gene.BASES)
            throw new IllegalArgumentException(String.format("Sequence with invalid length. Expected length %d, given %s", BASES, code));

        this.code = Arrays.stream(code.split(""))
                .map(Base::valueOf).toArray(Base[]::new);
    }

    public static Gene randomGene() {
        return new Gene(Stream.generate(Base::randomBase)
                .limit(Gene.BASES)
                .toArray(Base[]::new));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        stream(code).map(Base::name).forEach(builder::append);
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(code);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Gene other && Arrays.equals(other.code, this.code);
    }
}
