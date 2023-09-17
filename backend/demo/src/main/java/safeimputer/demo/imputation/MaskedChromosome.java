package safeimputer.demo.imputation;

import java.util.List;
import java.util.Optional;

public record MaskedChromosome(List<Optional<Gene>> genes) {
    int length() {
        return genes.size();
    }

    Optional<Gene> position(int position) {
        return genes.get(position);
    }
}
