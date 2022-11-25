package cardealer.carinfo;

import cardealer.Color;
import cardealer.GetRandom;
import cardealer.MakesAndModels;

import java.util.List;

/**
 * @author Moussa
 */
public class RandomCarInfoGenerator {

    private RandomCarInfoGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static CarInfo generateRandomCarInfo(MakesAndModels makesAndModels, int minYear, int yearRange) {
        final var make = GetRandom.getRandomElement(makesAndModels.getMakes());
        final var model = GetRandom.getRandomElement(makesAndModels.getModels(make));
        final var year = GetRandom.RANDOM_GEN.nextInt(yearRange) + minYear;
        final var color = GetRandom.getRandomElement(List.of(Color.values())).toString();

        return new CarInfo(make, model, year, color);
    }
}