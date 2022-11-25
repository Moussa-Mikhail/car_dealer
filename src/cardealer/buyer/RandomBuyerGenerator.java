package cardealer.buyer;

import cardealer.MakesAndModels;
import cardealer.NamesUtil;
import cardealer.carinfo.CarInfo;

/**
 * @author Moussa
 */
public class RandomBuyerGenerator {

    private RandomBuyerGenerator() {
        throw new IllegalStateException("Utility class");
    }

    public static LuxuryBuyer generateLuxuryBuyer() {

        var name = NamesUtil.getRandomName();

        var makesAndModels = MakesAndModels.getLuxuryMakesAndModels();

        var wantedCar = CarInfo.generateRandomCarInfo(makesAndModels, 2010, 10);

        return new LuxuryBuyer(name, wantedCar);
    }

    public static Buyer generateStandardBuyer() {
        var name = NamesUtil.getRandomName();

        var makesAndModels = MakesAndModels.getStandardMakesAndModels();

        var wantedCar = CarInfo.generateRandomCarInfo(makesAndModels, 2010, 10);

        return new StandardBuyer(name, wantedCar);
    }
}