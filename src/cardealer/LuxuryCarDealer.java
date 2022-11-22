package cardealer;

/**
 * @author Moussa
 */
public final class LuxuryCarDealer extends CarDealer {

    private static final int WARRANTY_PRICE = 5000;

    private static final Models LUXURY_MODELS = Models.luxuryModels();

    private static final int MIN_PRICE_IN_THOUSANDS = 80;

    private static final int PRICE_RANGE_IN_THOUSANDS = 60;

    public LuxuryCarDealer() {

        populateInventory(INITIAL_NUM_CARS, LUXURY_MODELS);

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