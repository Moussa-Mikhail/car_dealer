package cardealer.buyer;

import cardealer.carinfo.CarInfo;
import cardealer.utils.ModelsDataProvider;
import cardealer.utils.NamesUtil;

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
        ModelsDataProvider modelsDataProvider = ModelsDataProvider.getLuxuryModelsDataProvider();
        CarInfo wantedCar = CarInfo.generateRandomCarInfo(modelsDataProvider, 2010, 10);
        return new LuxuryBuyer(name, wantedCar);
    }

    @SuppressWarnings("unused")
    public static IBuyer generateStandardBuyer() {
        String name = NamesUtil.getRandomName();
        ModelsDataProvider modelsDataProvider = ModelsDataProvider.getStandardModelsDataProvider();
        CarInfo wantedCar = CarInfo.generateRandomCarInfo(modelsDataProvider, 2010, 10);
        return new StandardBuyer(name, wantedCar);
    }
}