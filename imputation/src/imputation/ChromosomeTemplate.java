package imputation;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/***
 * Represents a template of possible allele in a chromosome
 */
public class ChromosomeTemplate {
    private static final Random RNG = new Random();
    public record Variant(List<Gene> variants){}
    private final Variant[] variants;

    public ChromosomeTemplate(Variant[] variants) {
        this.variants = variants;
    }

    public HaploidChromosome randomHaploid() {
        Gene[] sequence = Arrays.stream(this.variants)
                .map(variant ->
                        variant.variants().get(
                        RNG.nextInt(variant.variants().size())))
                .toArray(Gene[]::new);

        return new HaploidChromosome(sequence);
    }

    public static ChromosomeTemplate fromPair(ChromosomePair pair) {
        int len = pair.length();
        Variant[] variants = new Variant[len];
        for(int i = 0; i < len; i++) {
            variants[i] = new Variant(List.of(pair.firstPosition(i), pair.secondPosition(i)));
        }

        return new ChromosomeTemplate(variants);
    }
}
