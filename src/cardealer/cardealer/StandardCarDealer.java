package cardealer.cardealer;

import cardealer.MakesAndModels;

/**
 * @author Moussa
 */
public final class StandardCarDealer extends AbstractCarDealer {

    private static final MakesAndModels MAKE_AND_MODELS = MakesAndModels.getStandardMakesAndModels();

    public StandardCarDealer() {
        populateInventory(INITIAL_NUM_CARS, MAKE_AND_MODELS);
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
