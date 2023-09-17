package test.imputation;

import imputation.ChromosomePair;
import imputation.Gene;
import imputation.Genepool;
import imputation.HaploidChromosome;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkageDisequilibriumTest {
    HaploidChromosome majors = new HaploidChromosome("AAAAGGGGCCCC");
    HaploidChromosome minors = new HaploidChromosome("CCCCGGGGAAAA");

    ChromosomePair pair = new ChromosomePair(majors, minors);

    HaploidChromosome a = new HaploidChromosome("AAAAGGGGAAAA");
    HaploidChromosome b = new HaploidChromosome("CCCCGGGGCCCC");

    HaploidChromosome c = new HaploidChromosome("CCCCGGGGAAAA");

    @Test
    void testSingletons() {
        Genepool singleton = new Genepool(List.of(majors));

        assertEquals(0.0, singleton.linkage_disequilibrium(0, 0,
                new Gene("AAAA"), new Gene("AAAA")));

        assertEquals(0.0, singleton.linkage_disequilibrium(1, 1,
                new Gene("GGGG"), new Gene("GGGG")));

        assertEquals(0.0, singleton.linkage_disequilibrium(1, 2,
                new Gene("AAAA"), new Gene("CCCC")));
    }

    @Test
    void testHalfandHalf() {
        Genepool pool = new Genepool(List.of(majors, majors, minors, minors));
        assertEquals(0.25, pool.linkage_disequilibrium(0, 0,
                new Gene("AAAA"), new Gene("AAAA")));

        assertEquals(0.25, pool.linkage_disequilibrium(0, 0,
                new Gene("AAAA"), new Gene("AAAA")));

        assertEquals(0, pool.linkage_disequilibrium(0, 1,
                new Gene("AAAA"), new Gene("GGGG")));
    }
}
