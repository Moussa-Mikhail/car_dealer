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

    public static CarInfo generateCarInfo(MakesAndModels makesAndModels, int minYear, int yearRange) {
        var make = GetRandom.getRandomElement(makesAndModels.getMakes());
        var model = GetRandom.getRandomElement(makesAndModels.getModels(make));
        var year = GetRandom.RANDOM_GEN.nextInt(yearRange) + minYear;
        var color = GetRandom.getRandomElement(List.of(Color.values())).toString();

        return new CarInfo(make, model, year, color);
    }
}