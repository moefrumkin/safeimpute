package safeimputer.demo.imputation;

import java.util.Random;
import java.util.List;

public class ChromosomePair {
    public static Random RNG = new Random();

    private final HaploidChromosome first, second;

        public ChromosomePair(HaploidChromosome first, HaploidChromosome second) {
        if(first.length() != second.length())
            throw new IllegalArgumentException("Paired chromosome must have the same length");

        this.first = first;
        this.second = second;
    }

    public static ChromosomePair random(int len) {
        return new ChromosomePair(HaploidChromosome.random(len),
                HaploidChromosome.random(len));
    }

    public ChromosomePair cross(double avg_crosses) {
        int len = first.length();
        boolean cross;
        double cross_prob = avg_crosses / len;

        Gene[] first = new Gene[len];
        Gene[] second = new Gene[len];

        for(int gene = 0; gene < len; gene++) {
            cross = RNG.nextDouble() < cross_prob;
            if(cross) {
                first[gene] = this.second.position(gene);
                second[gene] = this.first.position(gene);
            } else {
                first[gene] = this.first.position(gene);
                second[gene] = this.second.position(gene);
            }
        }
        return new ChromosomePair(
                new HaploidChromosome(first),
                new HaploidChromosome(second));
    }

    public static ChromosomePair cross(HaploidChromosome first, HaploidChromosome second, double avg_crosses) {
            return new ChromosomePair(first, second).cross(avg_crosses);
    }

    public double match() {
        int matches = 0;
        for(int gene = 0; gene < first.length(); gene++)
            if(first.position(gene) == second.position(gene))
                matches++;
        return (double) matches / first.length();
    }

    public int length() {
        return this.first.length();
    }

    public Gene firstPosition(int position) {
            return first.position(position);
    }

    public Gene secondPosition(int position) {
            return second.position(position);
    }

    public static double match(HaploidChromosome first, HaploidChromosome second) {
        return new ChromosomePair(first, second).match();
    }

    public Genepool toGenepool() {
            return new Genepool(List.of(first, second));
    }

    @Override
    public String toString() {
        return first.toString() + '\n' + second.toString();
    }

    public HaploidChromosome first() {
        return first;
    }

    public HaploidChromosome second() {
            return second;
    }
}
