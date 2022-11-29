package cardealer.transaction;

import static cardealer.cardealer.AbstractCarAndWarrantyDealer.WARRANTY_INFO;

/**
 * @author Moussa
 */
public final class WarrantyTransaction extends StandardTransaction {

    public WarrantyTransaction(String buyerName, int price) {
        super(buyerName, WARRANTY_INFO, price);
    }

    @Override
    public String toString() {
        return "WarrantyTransaction{} " + super.toString();
    }
}
