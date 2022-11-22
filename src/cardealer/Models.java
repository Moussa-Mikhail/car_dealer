package cardealer;

import java.util.Map;
import java.util.Set;

/**
 * @author Moussa
 */
public class Models {

    private final Map<String, Set<String>> makeToModels;

    public Models(Map<String, Set<String>> makeToModels) {

        this.makeToModels = makeToModels;
    }

    public static Models standardModels() {
        final var makeToModels = Map.of(
                "Toyota", Set.of("Camry", "Corolla", "RAV4"),
                "Honda", Set.of("Accord", "Civic", "CR-V"),
                "Ford", Set.of("F-150", "Fusion", "Mustang"),
                "Chevrolet", Set.of("Camaro", "Corvette", "Malibu"),
                "Subaru", Set.of("Crosstrek", "Forester", "Impreza")
        );

        return new Models(makeToModels);
    }

    public static Models luxuryModels() {

        final var makeToModels = Map.of(
                "Mercedes", Set.of("C-Class", "E-Class", "S-Class"),
                "BMW", Set.of("3-Series", "5-Series", "7-Series"),
                "Audi", Set.of("A4", "A6", "A8"),
                "Lexus", Set.of("ES", "GS", "LS")
        );

        return new Models(makeToModels);
    }

    public Set<String> getMakes() {
        return makeToModels.keySet();
    }

    public Set<String> getModels(String make) {
        return makeToModels.get(make);
    }
}