package cardealer.carinfo;

import cardealer.MakesAndModels;

/**
 * @author Moussa
 */
public class CarInfo {

    public static final int MIN_YEAR = 2000;
    public static final int YEAR_RANGE = 20;
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


    public static CarInfo generateRandomCarInfo(MakesAndModels makesAndModels) {
        return RandomCarInfoGenerator.generateRandomCarInfo(makesAndModels, MIN_YEAR, YEAR_RANGE);
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
