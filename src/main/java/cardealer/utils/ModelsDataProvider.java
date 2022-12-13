package cardealer.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Moussa
 */
public class ModelsDataProvider {
    private final Map<String, Set<String>> makeToModels;

    public ModelsDataProvider(Map<String, Set<String>> makeToModels) {
        this.makeToModels = makeToModels;
    }

    public static @NotNull ModelsDataProvider getStandardModelsDataProvider() {
        Map<String, Set<String>> makeToModels = Map.of(
                "Toyota", Set.of("Camry", "Corolla", "RAV4"),
                "Honda", Set.of("Accord", "Civic", "CR-V"),
                "Ford", Set.of("F-150", "Fusion", "Mustang"),
                "Chevrolet", Set.of("Camaro", "Corvette", "Malibu"),
                "Subaru", Set.of("Crosstrek", "Forester", "Impreza")
        );
        return new ModelsDataProvider(makeToModels);
    }

    public static @NotNull ModelsDataProvider getLuxuryModelsDataProvider() {
        Map<String, Set<String>> makeToModels = Map.of(
                "Mercedes", Set.of("C-Class", "E-Class", "S-Class"),
                "BMW", Set.of("3-Series", "5-Series", "7-Series"),
                "Audi", Set.of("A4", "A6", "A8"),
                "Lexus", Set.of("ES", "GS", "LS")
        );
        return new ModelsDataProvider(makeToModels);
    }

    public @NotNull Set<String> getMakes() {
        return makeToModels.keySet();
    }

    public Set<String> getModels(String make) {
        return makeToModels.get(make);
    }

    @Override
    public @NotNull String toString() {
        return "Models{" +
                "makeToModels=" + makeToModels +
                '}';
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        ModelsDataProvider modelsDataProvider = (ModelsDataProvider) other;
        return makeToModels.equals(modelsDataProvider.makeToModels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(makeToModels);
    }
}