package imputation;

import java.util.List;

public record Genepool(List<HaploidChromosome> genomes) {
    public double[][] matches() {
        int len = genomes.get(0).sequence.length;
        double[][] matches = new double[len][len];

        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                matches[i][j] = ChromosomePair.match(genomes.get(i), genomes.get(j));
            }
        }

        return matches;
    }

    public double linkage_disequilibrium(int a, int b, Gene aMaj, Gene bMaj) {
        int num_genomes = genomes.get(0).sequence.length;

        int a_count = 0;
        int b_count = 0;
        int ab_count = 0;

        for(HaploidChromosome genome: genomes) {
            boolean afound = genome.sequence[a] == aMaj;
            boolean bfound = genome.sequence[b] == bMaj;

            if(afound) a_count++;
            if(bfound) b_count++;
            if(bfound && afound) ab_count++;
        }

        double pa = (double) a_count /num_genomes;
        double pb = (double) b_count /num_genomes;
        double pab = (double) ab_count /num_genomes;

        return pab - pa * pb;
    }

    public double[][] linkage_disequilibria(ChromosomePair variants) {
        int genes = variants.first().sequence.length;
        double[][] lds = new double[genes][genes];
        for(int i = 0; i < genes; i++) {
            for(int j = 0; j < genes; j++) {
                lds[i][j] = linkage_disequilibrium(i, j, variants.first().sequence[i],
                        variants.first().sequence[j]);
            }
        }

        return lds;
    }
}
