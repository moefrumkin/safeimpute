package test.imputation;

import imputation.Base;
import imputation.Gene;
import imputation.HaploidChromosome;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HaploidChromosomeTest {
    public static final String stringCode = "AATGGGCTCGTA";
    public static final Gene[] code = {
            new Gene(new Base[]{Base.A, Base.A, Base.T, Base.G}),
            new Gene(new Base[]{Base.G, Base.G, Base.C, Base.T}),
            new Gene(new Base[]{Base.C, Base.G, Base.T, Base.A})
    };
    public static final HaploidChromosome sequence = new HaploidChromosome(code);

    @Test
    public void testParse() {
        assertEquals(sequence, new HaploidChromosome(stringCode));
    }
}
