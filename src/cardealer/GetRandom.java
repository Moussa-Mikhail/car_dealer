package cardealer;

import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * @author Moussa
 */
public class GetRandom {

    public static final Random RANDOM_GEN = new Random();

    private GetRandom() {
        throw new AssertionError("Utility class.");
    }

    public static <E> E getRandomElement(List<E> list) {

        final var randIdx = RANDOM_GEN.nextInt(list.size());

        return list.get(randIdx);
    }

    public static <E> E getRandomElement(Collection<E> collection) {
        // Copied from https://stackoverflow.com/a/40087987/18650633

        return collection.stream()
                .skip(RANDOM_GEN.nextInt(collection.size()))
                .findFirst().orElseThrow();
    }
}
