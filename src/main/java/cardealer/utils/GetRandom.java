package cardealer.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * @author Moussa
 */
public final class GetRandom {
    public static final Random RANDOM_GEN = new Random();

    private GetRandom() {
        throw new UnsupportedOperationException("Utility class.");
    }

    @SafeVarargs
    public static <E> E getRandomElement(E... array) {
        return getRandomElement(List.of(array));
    }

    public static <E> E getRandomElement(@NotNull List<E> list) {
        int randIdx = RANDOM_GEN.nextInt(list.size());
        return list.get(randIdx);
    }

    public static <E> @NotNull E getRandomElement(@NotNull Collection<E> collection) {
        // Copied from https://stackoverflow.com/a/40087987/18650633
        return collection.stream()
                .skip(RANDOM_GEN.nextInt(collection.size()))
                .findFirst().orElseThrow();
    }
}
