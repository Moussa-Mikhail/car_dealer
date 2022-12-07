package cardealer.carinfo;

import cardealer.utils.Color;
import cardealer.utils.GetRandom;
import cardealer.utils.ModelsDataProvider;

import java.util.List;
import java.util.Objects;

/**
 * @author Moussa
 */
public final class CarInfo {
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

    public static CarInfo generateRandomCarInfo(ModelsDataProvider modelsDataProvider, int minYear, int yearRange) {
        String make = GetRandom.getRandomElement(modelsDataProvider.getMakes());
        String model = GetRandom.getRandomElement(modelsDataProvider.getModels(make));
        int year = GetRandom.RANDOM_GEN.nextInt(yearRange) + minYear;
        String color = GetRandom.getRandomElement(List.of(Color.values())).toString();
        return new CarInfo(make, model, year, color);
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return String.format("%s %d %s %s", color, year, make, model);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        CarInfo carInfo = (CarInfo) other;
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
        return Objects.hash(make, model, year, color);
    }
}

