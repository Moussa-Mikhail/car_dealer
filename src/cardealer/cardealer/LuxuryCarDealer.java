package cardealer.cardealer;

import cardealer.MakesAndModels;
import cardealer.buyer.Buyer;

/**
 * @author Moussa
 */
public final class LuxuryCarDealer extends AbstractCarDealer {

    private static final int INITIAL_NUM_CARS = 600;
    private static final int WARRANTY_PRICE = 5000;
    private static final MakesAndModels LUXURY_MAKE_AND_MODELS = MakesAndModels.getLuxuryMakesAndModels();
    private static final int MIN_PRICE_IN_THOUSANDS = 80;
    private static final int PRICE_RANGE_IN_THOUSANDS = 60;
    private static final int MIN_YEAR = 2010;
    private static final int YEAR_RANGE = 10;

    public LuxuryCarDealer() {
        populateInventory(INITIAL_NUM_CARS, LUXURY_MAKE_AND_MODELS, MIN_YEAR, YEAR_RANGE);
        setPrices(MIN_PRICE_IN_THOUSANDS, PRICE_RANGE_IN_THOUSANDS);
    }

    @Override
    public String toString() {
        return "LuxuryCarDealer{} " + super.toString();
    }

    @Override
    public void sellWarranty(Buyer buyer) {
        sellWarranty(buyer, WARRANTY_PRICE);
    }

    @Override
    public int getWarrantyPrice() {
        return WARRANTY_PRICE;
    }
}