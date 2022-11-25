package cardealer.carinfo;

import cardealer.Color;
import cardealer.GetRandom;
import cardealer.MakesAndModels;

import java.util.List;

/**
 * @author Moussa
 */
public class CarInfo {

    private final String make;
    private final String model;
    private final int year;
    private final String color;

    public CarInfo(String make, String model, int year, String color) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
    }

    public static CarInfo generateRandomCarInfo(MakesAndModels makesAndModels, int minYear, int yearRange) {
        var make = GetRandom.getRandomElement(makesAndModels.getMakes());
        var model = GetRandom.getRandomElement(makesAndModels.getModels(make));
        var year = GetRandom.RANDOM_GEN.nextInt(yearRange) + minYear;
        var color = GetRandom.getRandomElement(List.of(Color.values())).toString();

        return new CarInfo(make, model, year, color);
    }

    @Override
    public String toString() {
        return "CarInfo{" + "make='" + make + '\'' + ", model='" + model + '\'' + ", year=" + year + ", color='" + color + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CarInfo carInfo = (CarInfo) o;

        if (year != carInfo.year) {
            return false;
        }
        if (!make.equals(carInfo.make)) {
            return false;
        }
        if (!model.equals(carInfo.model)) {
            return false;
        }

        return color.equals(carInfo.color);
    }

    @Override
    public int hashCode() {
        int result = make.hashCode();
        result = 31 * result + model.hashCode();
        result = 31 * result + year;
        result = 31 * result + color.hashCode();

        return result;
    }

    public String printableString() {
        return String.format("%s %d %s %s", color, year, make, model);
    }
}

