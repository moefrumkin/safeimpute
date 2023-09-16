package imputation;

import java.util.Random;

public record ChromosomePair(HaploidChromosome first, HaploidChromosome second) {
    public static Random RNG = new Random();

    public static ChromosomePair random(int len) {
        return new ChromosomePair(HaploidChromosome.random(len),
                HaploidChromosome.random(len));
    }
    public ChromosomePair cross(double avg_crosses) {
        int len = first.sequence.length;
        boolean cross = false;
        double cross_prob = avg_crosses / (int) len;

        Gene[] first = new Gene[len];
        Gene[] second = new Gene[len];

        for(int gene = 0; gene < len; gene++) {
            cross = RNG.nextDouble() < cross_prob;
            if(cross) {
                first[gene] = this.second.sequence[gene];
                second[gene] = this.first.sequence[gene];
            } else {
                first[gene] = this.first.sequence[gene];
                second[gene] = this.second.sequence[gene];
            }
        }
        return new ChromosomePair(
                new HaploidChromosome(first),
                new HaploidChromosome(second));
    }

    public double match() {
        int matches = 0;
        for(int gene = 0; gene < first.sequence.length; gene++)
            if(first.sequence[gene] == second.sequence[gene])
                matches++;
        return (double) matches / first.sequence.length;
    }

    public static double match(HaploidChromosome first, HaploidChromosome second) {
        return new ChromosomePair(first, second).match();
    }

    @Override
    public String toString() {
        return first.toString() + '\n' + second.toString();
    }
}
