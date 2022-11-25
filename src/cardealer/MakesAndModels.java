package cardealer;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author Moussa
 */
public class MakesAndModels {

    private final Map<String, Set<String>> makeToModels;

    public MakesAndModels(Map<String, Set<String>> makeToModels) {
        this.makeToModels = makeToModels;
    }

    public Set<String> getMakes() {
        return makeToModels.keySet();
    }

    public Set<String> getModels(String make) {
        return makeToModels.get(make);
    }

    public static MakesAndModels getStandardMakesAndModels() {

        var makeToModels = Map.of(
                "Toyota", Set.of("Camry", "Corolla", "RAV4"),
                "Honda", Set.of("Accord", "Civic", "CR-V"),
                "Ford", Set.of("F-150", "Fusion", "Mustang"),
                "Chevrolet", Set.of("Camaro", "Corvette", "Malibu"),
                "Subaru", Set.of("Crosstrek", "Forester", "Impreza")
        );

        return new MakesAndModels(makeToModels);
    }

    public static MakesAndModels getLuxuryMakesAndModels() {

        var makeToModels = Map.of(
                "Mercedes", Set.of("C-Class", "E-Class", "S-Class"),
                "BMW", Set.of("3-Series", "5-Series", "7-Series"),
                "Audi", Set.of("A4", "A6", "A8"),
                "Lexus", Set.of("ES", "GS", "LS")
        );

        return new MakesAndModels(makeToModels);
    }

    @Override
    public String toString() {
        return "Models{" +
                "makeToModels=" + makeToModels +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MakesAndModels makesAndModels = (MakesAndModels) o;

        return makeToModels.equals(makesAndModels.makeToModels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(makeToModels);
    }
}