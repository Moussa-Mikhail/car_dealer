package cardealer.cardealer;

import cardealer.buyer.IBuyer;
import cardealer.carinfo.CarInfo;
import cardealer.transaction.WarrantyTransaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * @author Moussa
 */
public abstract class AbstractCarAndWarrantyDealer extends AbstractCarDealer implements ISellsWarranty {
    protected static final String WARRANTY_DESCRIPTION = "Extended Warranty";
    protected static final int WARRANTY_PRICE = 2000;
    private static final Logger LOGGER = LogManager.getLogger(AbstractCarAndWarrantyDealer.class);

    protected AbstractCarAndWarrantyDealer() {
        super();
        LOGGER.info("Abstract Car and Warranty dealer created.");
    }

    public static @NotNull String getWarrantyDescription() {
        return WARRANTY_DESCRIPTION;
    }

    @Override
    public void sellWarranty(@NotNull IBuyer buyer, CarInfo carInfo) {
        int price = calcWarrantyPrice(carInfo);
        totalSales += price;
        WarrantyTransaction transaction = new WarrantyTransaction(buyer.getName(), price);
        transactions.addTransaction(transaction);
        LOGGER.info("Warranty sold to {} for {}.", buyer.getName(), price);
    }

    @Override
    public int calcWarrantyPrice(CarInfo carInfo) {
        return WARRANTY_PRICE;
    }
}
