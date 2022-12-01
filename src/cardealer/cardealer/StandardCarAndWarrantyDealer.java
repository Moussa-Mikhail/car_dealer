package cardealer.cardealer;

import cardealer.ModelsDataProvider;

/**
 * @author Moussa
 */
public final class StandardCarAndWarrantyDealer extends AbstractCarAndWarrantyDealer {
    private static final int MIN_YEAR = 2010;
    private static final int YEAR_RANGE = 10;
    private static final ModelsDataProvider STANDARD_MODELS_DATA_PROVIDER = ModelsDataProvider.getStandardModelsDataProvider();

    public StandardCarAndWarrantyDealer() {
        populateInventory(INITIAL_NUM_CARS, STANDARD_MODELS_DATA_PROVIDER, MIN_YEAR, YEAR_RANGE);
        setPrices(MIN_PRICE_IN_THOUSANDS, PRICE_RANGE_IN_THOUSANDS);
    }

    @Override
    public String toString() {
        return "CarDealer{" +
                "carInfoToNumber=" + carInfoToNumber +
                ", carInfoToPrice=" + carInfoToPrice +
                ", transactions=" + transactions +
                ", numCarsSold=" + numCarsSold +
                ", totalSales=" + totalSales +
                '}';
    }

}
