package cardealer;

/**
 * @author Moussa
 */
public class Buyer {

    private final String name;

    private final CarInfo wantedCar;

    public Buyer(String name, CarInfo wantedCar) {
        this.name = name;
        this.wantedCar = wantedCar;
    }

    public static Buyer generateRandomBuyer() {

        final var randomName = Name.getRandomName();

        final var carInfo = CarInfo.generateRandomCarInfo();

        return new Buyer(randomName, carInfo);
    }

    public String getName() {
        return name;
    }

    public CarInfo getWantedCar() {
        return wantedCar;
    }

}
