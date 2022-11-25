package cardealer.buyer;

import cardealer.CarInfo;
import cardealer.Models;
import cardealer.Name;

/**
 * @author Moussa
 */
public class LuxuryBuyer extends Buyer {

    private static final Models MODELS = Models.luxuryModels();

    public LuxuryBuyer(String name, CarInfo wantedCar) {
        super(name, wantedCar);
    }

    public static LuxuryBuyer generateRandomBuyer() {
        String randomName = Name.getRandomName();
        CarInfo randCar = CarInfo.generateRandomCarInfo(MODELS);
        return new LuxuryBuyer(randomName, randCar);
    }

    @Override
    public String toString() {
        return "LuxuryBuyer{} " + super.toString();
    }
}

