package safeimputer.demo.imputation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/***
 * Represents a template of possible allele in a chromosome
 */
public class ChromosomeTemplate {
    private static final Random RNG = new Random();
    public record Variant(List<Gene> variants){
        public Gene randomVariant() {
            return variants.get(RNG.nextInt(variants.size()));
        }
    }
    private final Variant[] variants;

    public ChromosomeTemplate(Variant[] variants) {
        this.variants = variants;
    }

    public static ChromosomeTemplate fromPair(ChromosomePair pair) {
        int len = pair.length();
        Variant[] variants = new Variant[len];
        for(int i = 0; i < len; i++) {
            variants[i] = new Variant(List.of(pair.firstPosition(i), pair.secondPosition(i)));
        }

        return new ChromosomeTemplate(variants);
    }

    public static ChromosomeTemplate fromPair(ChromosomePair pair, int maxMajor, int maxMinor) {
        Variant[] variants = new Variant[pair.length()];

        for(int i = 0; i < pair.length(); i++) {
            List<Gene> variant = new ArrayList<>();
            Gene major = pair.firstPosition(i);
            Gene minor = pair.secondPosition(i);
            IntStream.range(0, RNG.nextInt(maxMinor, maxMajor)).forEach(a -> variant.add(major));
            IntStream.range(0, RNG.nextInt(1, maxMinor)).forEach(a -> variant.add(minor));
            variants[i] = new Variant(variant);
        }

        return new ChromosomeTemplate(variants);
    }

    public Genepool randomPool(int sequences) {
        return new Genepool(Stream.generate(this::randomChromosome)
                .limit(sequences).toList());
    }

    private HaploidChromosome randomChromosome() {
        return new HaploidChromosome(Arrays.stream(variants)
                .map(Variant::randomVariant)
                .toArray(Gene[]::new));
    }
}
