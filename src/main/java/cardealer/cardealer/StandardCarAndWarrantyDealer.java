package cardealer.cardealer;

import cardealer.utils.ModelsDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Moussa
 */
public final class StandardCarAndWarrantyDealer extends AbstractCarAndWarrantyDealer {
    private static final int MIN_YEAR = 2010;
    private static final int YEAR_RANGE = 10;
    private static final ModelsDataProvider STANDARD_MODELS_DATA_PROVIDER = ModelsDataProvider.getStandardModelsDataProvider();
    private static final Logger LOGGER = LogManager.getLogger(StandardCarAndWarrantyDealer.class);

    public StandardCarAndWarrantyDealer() {
        super();
        LOGGER.info("StandardCarAndWarrantyDealer created.");
        populateInventory(INITIAL_NUM_CARS, STANDARD_MODELS_DATA_PROVIDER, MIN_YEAR, YEAR_RANGE);
        setPrices(MIN_PRICE_IN_THOUSANDS, PRICE_RANGE_IN_THOUSANDS);
    }
}
