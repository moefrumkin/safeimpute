package safeimputer.demo.imputation;

import java.util.List;

/***
 * Represents either a concrete chromosome
 */
public interface Chromosome {
    public static final int GENES = 4;

    /***
     * Give
     * @param position
     * @return
     */
    List<Gene> variants(int position);
}
