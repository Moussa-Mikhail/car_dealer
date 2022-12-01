package cardealer.buyer;

import cardealer.ModelsDataProvider;
import cardealer.NamesUtil;
import cardealer.carinfo.CarInfo;

/**
 * @author Moussa
 */
@SuppressWarnings("unused")
public class RandomBuyerGenerator {

    private RandomBuyerGenerator() {
        throw new IllegalStateException("Utility class");
    }

    @SuppressWarnings("unused")
    public static LuxuryBuyer generateLuxuryBuyer() {

        String name = NamesUtil.getRandomName();

        ModelsDataProvider makesAndModels = ModelsDataProvider.getLuxuryMakesAndModels();

        CarInfo wantedCar = CarInfo.generateRandomCarInfo(makesAndModels, 2010, 10);

        return new LuxuryBuyer(name, wantedCar);
    }

    @SuppressWarnings("unused")
    public static IBuyer generateStandardBuyer() {
        String name = NamesUtil.getRandomName();

        ModelsDataProvider makesAndModels = ModelsDataProvider.getStandardMakesAndModels();

        CarInfo wantedCar = CarInfo.generateRandomCarInfo(makesAndModels, 2010, 10);

        return new StandardBuyer(name, wantedCar);
    }
}