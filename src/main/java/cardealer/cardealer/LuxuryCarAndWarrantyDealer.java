package cardealer.cardealer;

import cardealer.carinfo.CarInfo;
import cardealer.utils.ModelsDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Moussa
 */
public final class LuxuryCarAndWarrantyDealer extends AbstractCarAndWarrantyDealer {
    private static final int INITIAL_NUM_CARS = 600;
    private static final int WARRANTY_PRICE = 5000;
    private static final ModelsDataProvider LUXURY_MODELS_DATA_PROVIDER = ModelsDataProvider.getLuxuryModelsDataProvider();
    private static final int MIN_PRICE_IN_THOUSANDS = 80;
    private static final int PRICE_RANGE_IN_THOUSANDS = 60;
    private static final int MIN_YEAR = 2010;
    private static final int YEAR_RANGE = 10;
    private static final Logger LOGGER = LogManager.getLogger(LuxuryCarAndWarrantyDealer.class);

    public LuxuryCarAndWarrantyDealer() {
        super();
        LOGGER.info("LuxuryCarAndWarrantyDealer created.");
        populateInventory(INITIAL_NUM_CARS, LUXURY_MODELS_DATA_PROVIDER, MIN_YEAR, YEAR_RANGE);
        setPrices(MIN_PRICE_IN_THOUSANDS, PRICE_RANGE_IN_THOUSANDS);
    }

    @Override
    public int calcWarrantyPrice(CarInfo carInfo) {
        return WARRANTY_PRICE;
    }
}