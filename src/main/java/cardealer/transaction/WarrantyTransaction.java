package cardealer.transaction;

import cardealer.cardealer.AbstractCarAndWarrantyDealer;

/**
 * @author Moussa
 */
public final class WarrantyTransaction extends StandardTransaction {
    public WarrantyTransaction(String buyerName, int price) {
        super(buyerName, AbstractCarAndWarrantyDealer.getWarrantyDescription(), price);
    }

    @Override
    public String toString() {
        return "WarrantyTransaction{} " + super.toString();
    }
}
