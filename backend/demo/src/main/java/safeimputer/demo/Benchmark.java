package safeimputer.demo;

import safeimputer.demo.imputation.ChromosomePair;
import safeimputer.demo.imputation.Genepool;
import safeimputer.demo.imputation.HaploidChromosome;
import safeimputer.demo.imputation.MaskedChromosome;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public record Benchmark(int genomeLength, int genepoolSize, int generations, double lowerNoise, double upperNoise, int strides) {
    public static class BenchmarkBuilder {
        int genomeLength = 250;
        int genepoolSize = 200;

        int generations = 25;

        double lowerNoise = 0;
        double upperNoise = 1.0;
        int strides = 10;

        public BenchmarkBuilder() {}

        public BenchmarkBuilder genonomeLength(int length) {
            this.genomeLength = length;
            return this;
        }

        public BenchmarkBuilder genepoolSize(int size) {
            this.genepoolSize = size;
            return this;
        }

        public BenchmarkBuilder generations(int generations) {
            this.generations = generations;
            return this;
        }

        public BenchmarkBuilder lowerNoise(double noise) {
            this.lowerNoise = noise;
            return this;
        }

        public BenchmarkBuilder upperNoise(double noise) {
            this.upperNoise = noise;
            return this;
        }

        public BenchmarkBuilder strides(int strides) {
            this.strides = strides;
            return this;
        }

        public Benchmark build() {
            return new Benchmark(genomeLength, genepoolSize, generations, lowerNoise, upperNoise, strides);
        }
    }

    public Map<Double, Double> run(String savedir) {
        ChromosomePair chromie = HaploidChromosome.random(genomeLength).mutate(0.75);
        Genepool genepool = chromie.toGenepool();

        Map<Double, Double> result = new HashMap<>();

        for(int i = 0; i < generations; i++) {
            genepool = genepool.reproduce(0.5, genepoolSize / (generations - i));
        }

        try {
            genepool.save(savedir);
            chromie.save(savedir + ".maj");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(double test = lowerNoise; test <= upperNoise; test += (upperNoise - lowerNoise) / strides) {
            Genepool distorted = genepool.distort((int) (genepoolSize * test), chromie);

            double accuracy = 0;

            for(HaploidChromosome genome: distorted.genomes()) {
                MaskedChromosome masked = genome.mask(2);
                HaploidChromosome imputed = distorted.mostLikelyImpute(masked);
                accuracy += ChromosomePair.match(imputed, genome);
            }

            result.put(test, accuracy / distorted.size());

            try {
                genepool.save(String.format("%s.%1.3f", savedir, test));
                chromie.save(String.format("%s.%1.3f.maj", savedir, test));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }
}
