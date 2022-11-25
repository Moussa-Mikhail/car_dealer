package cardealer.transaction;

/**
 * @author Moussa
 */
public interface ITransaction {
    /**
     * @return the name of the buyer
     */
    String getBuyerName();

    /**
     * @return the info about the transaction
     */
    String getInfo();

    /**
     * @return the price of the transaction
     */
    int getPrice();
}
