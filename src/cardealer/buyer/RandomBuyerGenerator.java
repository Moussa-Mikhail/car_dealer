package cardealer.buyer;

import cardealer.ModelsDataProvider;
import cardealer.NamesUtil;
import cardealer.carinfo.CarInfo;

/**
 * @author Moussa
 */
public class RandomBuyerGenerator {

    private RandomBuyerGenerator() {
        throw new IllegalStateException("Utility class");
    }

    @SuppressWarnings("unused")
    public static LuxuryBuyer generateLuxuryBuyer() {

        var name = NamesUtil.getRandomName();

        var makesAndModels = ModelsDataProvider.getLuxuryMakesAndModels();

        var wantedCar = CarInfo.generateRandomCarInfo(makesAndModels, 2010, 10);

        return new LuxuryBuyer(name, wantedCar);
    }

    @SuppressWarnings("unused")
    public static IBuyer generateStandardBuyer() {
        var name = NamesUtil.getRandomName();

        var makesAndModels = ModelsDataProvider.getStandardMakesAndModels();

        var wantedCar = CarInfo.generateRandomCarInfo(makesAndModels, 2010, 10);

        return new StandardBuyer(name, wantedCar);
    }
}