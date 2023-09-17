package main.java.safeimputer.demo.imputation;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;

public record Genepool(List<HaploidChromosome> genomes) {
    private static final Random RNG = new Random();

    public static Genepool weighted(ChromosomePair pair, int major, int minor) {
        List<HaploidChromosome> genomes = new ArrayList<>();

        IntStream.range(0, major).forEach(i -> genomes.add(pair.first()));
        IntStream.range(0, minor).forEach(i -> genomes.add(pair.second()));

        return new Genepool(genomes);
    }

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

    public int genomeLength() {
        return genomes.get(0).length();
    }

    private HaploidChromosome randomGenome() {
        return genomes.get(RNG.nextInt(genomes.size()));
    }

    public double linkage_disequilibrium(int a, int b, Gene aMaj, Gene bMaj) {
        int num_genomes = genomes.size();

        int a_count = 0;
        int b_count = 0;
        int ab_count = 0;

        for(HaploidChromosome genome: genomes) {
            boolean afound = genome.position(a).equals(aMaj);
            boolean bfound = genome.position(b).equals(bMaj);

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
        int genes = genomeLength();
        double[][] lds = new double[genes][genes];
        for(int i = 0; i < genes; i++) {
            for(int j = i; j < genes; j++) {
                lds[i][j] = linkage_disequilibrium(i, j, variants.firstPosition(i),
                        variants.firstPosition(j));

                lds[j][i] = lds [i][j];
            }
        }

        return lds;
    }

    public double[] ldByDistance(ChromosomePair variants) {
        double[][] ld = linkage_disequilibria(variants);

        int[] counts = new int[genomeLength()];
        double[] result = new double[genomeLength()];

        for(int i = 0; i < genomeLength(); i++) {
            for(int j = i; j < genomeLength(); j++) {
                int dist = j - i;
                result[dist] = ((counts[dist] * result[dist]) + ld[i][j]) / (counts[dist] + 1);
                counts[dist]++;
            }
        }

        return result;
    }

    public Genepool reproduce(double rec_rate, int size) {
        List<HaploidChromosome> children = new ArrayList<>(size);

        /*for(int i = 0; i < len; i++) {
            for(int j = i; j < len; j++) {
                if(children.size() > cap) return new Genepool(children);
                if (RNG.nextDouble() < prune_rate)
                    children.add(new ChromosomePair(genomes.get(i), genomes.get(j)).cross(rec_rate).first());
            }
        }*/
        for(int i = 0; i < size; i++) {
            var cross = ChromosomePair.cross(randomGenome(), randomGenome(), rec_rate);
            children.add(cross.first());
            //children.add(cross.second());
        }

        return new Genepool(children);
    }

    /**
     * Major Allele Probabilities
     * @param pair The major and minor allele pairs, the first sequence is the only one used, and is considered the major allele.
     * @return Array of MAPs by variant
     */
    public double[] maps(ChromosomePair pair) {
        double[] mafs = new double[genomeLength()];

        for(int i = 0; i < genomeLength(); i++) {
            mafs[i] = map(i, pair.firstPosition(i));
        }

        return mafs;
    }

    private double map(int position, Gene major) {
        int count = 0;
        for(var genome: genomes)
            if(genome.position(position).equals(major))
                count++;

        return (double) count / genomes.size();
    }

    public HaploidChromosome mostLikelyImpute(MaskedChromosome in) {
        Gene[] res = new Gene[in.length()];

        for(int i = 0; i < in.length(); i++) {
            if(in.position(i).isPresent()) {
                res[i] = in.position(i).get();
            } else {
                res[i] = mostFrequent(i);
            }
        }

        return new HaploidChromosome(res);
    }

    public Gene mostFrequent(int position) {
        Map<Gene, Integer> tally = new HashMap<>();

        for(HaploidChromosome genome: genomes) {
            tally.putIfAbsent(genome.position(position), 0);
            tally.compute(genome.position(position), (gene, count) -> count + 1);
        }

        return Collections.max(tally.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public void save(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);

        for(HaploidChromosome chrom: this.genomes) {
            fileWriter.write(chrom.rawString());
            fileWriter.write('\n');
        }

        fileWriter.close();
    }

    public Genepool load(String fileName) throws IOException {
        try (var lineStream = Files.lines(Path.of(fileName))) {
            return new Genepool(lineStream.map(HaploidChromosome::new).toList());
        }
    }

    double similarity() {
        int len = genomes.size();
        int pairs = len * (len - 1) /2;
        double[][] matches = matches();

        double sum = 0;
        for(int i = 0; i < len; i++) {
            for(int j = i + 1; j < len; j++) {
                sum += matches[i][j];
            }
        }

        return sum / pairs;
    }

    @Override
    public String toString() {
        return String.format("Genepool (%d genomes of %d genes, %1.4f similarity)", genomes.size(), genomeLength(), similarity());
    }
}
