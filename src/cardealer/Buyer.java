package cardealer;

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
