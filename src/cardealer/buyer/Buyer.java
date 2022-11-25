package cardealer.buyer;

import cardealer.CarInfo;
import cardealer.Models;
import cardealer.Name;

/**
 * @author Moussa
 */
public class Buyer {

    private static final Models MODELS = Models.standardModels();
    protected final String name;
    protected final CarInfo wantedCar;

    public Buyer(String name, CarInfo wantedCar) {
        this.name = name;
        this.wantedCar = wantedCar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Buyer buyer = (Buyer) o;

        if (!name.equals(buyer.name)) {
            return false;
        }

        return wantedCar.equals(buyer.wantedCar);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + wantedCar.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "name='" + name + '\'' +
                ", wantedCar=" + wantedCar +
                '}';
    }

    public static Buyer generateRandomBuyer(Models models) {
        final var name = Name.getRandomName();
        final var carInfo = CarInfo.generateRandomCarInfo(models);
        return new Buyer(name, carInfo);
    }

    public static Buyer generateRandomBuyer() {
        return generateRandomBuyer(MODELS);
    }

    public String getName() {
        return name;
    }

    public CarInfo getWantedCar() {
        return wantedCar;
    }

}
