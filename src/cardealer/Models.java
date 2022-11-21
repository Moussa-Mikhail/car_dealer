package cardealer;

import java.util.Map;
import java.util.Set;

/**
 * @author Moussa
 */
public class Models {

    private static final Map<String, Set<String>> MAKE_TO_MODELS = Map.of(
            "Toyota", Set.of("Camry", "Corolla", "RAV4"),
            "Honda", Set.of("Accord", "Civic", "CR-V"),
            "Ford", Set.of("F-150", "Fusion", "Mustang"),
            "Chevrolet", Set.of("Camaro", "Corvette", "Malibu"),
            "Subaru", Set.of("Crosstrek", "Forester", "Impreza")
    );

    private Models() {
        throw new AssertionError("Utility class.");
    }

    public static Set<String> getMakes() {
        return MAKE_TO_MODELS.keySet();
    }

    public static Set<String> getModels(String make) {
        return MAKE_TO_MODELS.get(make);
    }
}