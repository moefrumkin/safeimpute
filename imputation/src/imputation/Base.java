package imputation;

import java.util.Random;

public enum Base {
    A, T, G, C;

    private static final Random RNG = new Random();

    public static Base randomBase() {
        Base[] bases = values();
        return bases[RNG.nextInt(bases.length)];
    }
}
