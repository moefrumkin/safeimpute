import imputation.ChromosomePair;
import imputation.ChromosomeTemplate;
import imputation.Genepool;
import imputation.HaploidChromosome;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting!");

        ChromosomePair chromie = ChromosomePair.random(10);
        System.out.println(chromie);

        ChromosomeTemplate template = ChromosomeTemplate.fromPair(chromie);

        List<HaploidChromosome> genomes = Stream.generate(template::randomHaploid)
                .limit(100).toList();

        Genepool genepool = new Genepool(genomes);

        System.out.println("\n");

        for(HaploidChromosome chrom : genomes) {
            System.out.println(chrom);
        }

        double[][] matches = genepool.matches();

        for (double[] match : matches) {
            for (int j = 0; j < matches.length; j++) {
                System.out.print(match[j] + " ");
            }
            System.out.println();
        }

        for(int i = 0; i < 100; i++) {
            genepool = genepool.reproduce(0, 0.05, 5000);
            System.out.printf("\nGen %d(%d)\n", i, genepool.genomes().size());
            double[][] lds = genepool.linkage_disequilibria(chromie);
            for (double[] ld : lds) {
                for (double v : ld) {
                    System.out.printf("%2.4f ", v);
                }
                System.out.println();
            }
        }
    }
}