package test.imputation;

import imputation.Base;
import imputation.Gene;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GeneTest {
    @Test
    public void testParse() {
        assertEquals(new Gene(new Base[]{Base.A, Base.T, Base.C, Base.G}),
                new Gene("ATCG"));
    }
}