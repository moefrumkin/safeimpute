import imputation.ChromosomePair;
import imputation.ChromosomeTemplate;
import imputation.Genepool;
import imputation.HaploidChromosome;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting!");

        ChromosomePair chromie = HaploidChromosome.random(500).mutate(0.75);
        System.out.println(chromie);

        ChromosomeTemplate template = ChromosomeTemplate.fromPair(chromie, 16, 4);

        int pop_size = 20;

        Genepool genepool = template.randomPool(20);//Genepool.weighted(chromie, 7, 3);

        System.out.println(genepool);

        System.out.println(Arrays.toString(genepool.maps(chromie)));

        for(int i = 0; i < 500; i++) {
            pop_size = (int) (1.1 * pop_size);
            genepool = genepool.reproduce(0.25, pop_size);
            System.out.printf("\nGen %d: %s\n", i, genepool);
            double[] lds = genepool.ldByDistance(chromie);
            String ldformat = Arrays.stream(lds)
                    .mapToObj(d -> String.format("%1.5f ", d))
                    .collect(Collectors.joining());
            System.out.println(ldformat);
            System.out.println(Arrays.toString(genepool.maps(chromie)));

            System.out.println();
        }

        try {
            genepool.save("genepool2");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}