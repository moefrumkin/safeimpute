package imputation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public record Genepool(List<HaploidChromosome> genomes) {
    private static final Random RNG = new Random();

    public double[][] matches() {
        int len = genomes.size();
        double[][] matches = new double[len][len];

        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                matches[i][j] = ChromosomePair.match(genomes.get(i), genomes.get(j));
            }
        }

        return matches;
    }

    public double linkage_disequilibrium(int a, int b, Gene aMaj, Gene bMaj) {
        int num_genomes = genomes.size();

        int a_count = 0;
        int b_count = 0;
        int ab_count = 0;

        for(HaploidChromosome genome: genomes) {
            boolean afound = genome.position(a) == aMaj;
            boolean bfound = genome.position(b) == bMaj;

            if(afound) a_count++;
            if(bfound) b_count++;
            if(bfound && afound) ab_count++;
        }

        double pa = (double) a_count / num_genomes;
        double pb = (double) b_count / num_genomes;
        double pab = (double) ab_count / num_genomes;

        return pab - pa * pb;
    }

    public double[][] linkage_disequilibria(ChromosomePair variants) {
        int genes = variants.length();
        double[][] lds = new double[genes][genes];
        for(int i = 0; i < genes; i++) {
            for(int j = 0; j < genes; j++) {
                lds[i][j] = linkage_disequilibrium(i, j, variants.firstPosition(i),
                        variants.firstPosition(j));
            }
        }

        return lds;
    }

    public Genepool reproduce(double rec_rate, double prune_rate, int cap) {
        int len = genomes().size();
        List<HaploidChromosome> children = new ArrayList<>();

        for(int i = 0; i < len; i++) {
            for(int j = 0; j < i; j++) {
                if(children.size() > cap) return new Genepool(children);
                if (RNG.nextDouble() < prune_rate)
                    children.add(new ChromosomePair(genomes.get(i), genomes.get(j)).cross(rec_rate).first());
            }
        }

        return new Genepool(children);
    }
}
