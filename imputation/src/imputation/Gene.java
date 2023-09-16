package imputation;

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
}
