package cardealer.cardealer;

import cardealer.ModelsDataProvider;

/**
 * @author Moussa
 */
public final class StandardCarDealer extends AbstractCarDealer {
    private static final int MIN_YEAR = 2010;
    private static final int YEAR_RANGE = 10;
    private static final ModelsDataProvider MAKE_AND_MODELS = ModelsDataProvider.getStandardMakesAndModels();

    public StandardCarDealer() {
        populateInventory(INITIAL_NUM_CARS, MAKE_AND_MODELS, MIN_YEAR, YEAR_RANGE);
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
