package cardealer.cardealer;

import cardealer.buyer.IBuyer;
import cardealer.carinfo.CarInfo;
import cardealer.transaction.WarrantyTransaction;

/**
 * @author Moussa
 */
public abstract class AbstractCarAndWarrantyDealer extends AbstractCarDealer implements ISellsWarranty {
    protected static final String WARRANTY_DESCRIPTION = "Extended Warranty";
    protected static final int WARRANTY_PRICE = 2000;

    public static String getWarrantyDescription() {
        return WARRANTY_DESCRIPTION;
    }

    @Override
    public void sellWarranty(IBuyer buyer, CarInfo carInfo) {
        int price = calcWarrantyPrice(carInfo);
        totalSales += price;
        WarrantyTransaction transaction = new WarrantyTransaction(buyer.getName(), price);
        transactions.addTransaction(transaction);
    }

    @Override
    public int calcWarrantyPrice(CarInfo carInfo) {
        return WARRANTY_PRICE;
    }
}
