package cardealer.enums;

import cardealer.cardealer.ISellsCars;
import cardealer.cardealer.LuxuryCarAndWarrantyDealer;
import cardealer.cardealer.StandardCarAndWarrantyDealer;

/**
 * @author Moussa
 */
public enum CarDealerType {
    STANDARD(new StandardCarAndWarrantyDealer(), "Standard"),
    LUXURY(new LuxuryCarAndWarrantyDealer(), "Luxury");
    private ISellsCars carDealer;
    private String type;

    CarDealerType(ISellsCars carDealer, String type) {
        this.carDealer = carDealer;
        this.type = type;
    }

    public ISellsCars getCarDealer() {
        return carDealer;
    }

    public String getType() {
        return type;
    }
}
