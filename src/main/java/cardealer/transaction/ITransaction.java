package cardealer.transaction;

/**
 * @author Moussa
 */
public interface ITransaction {
    /**
     * Returns the buyer's name.
     *
     * @return the name of the buyer
     */
    String getBuyerName();

    /**
     * Returns the description of the transaction.
     *
     * @return the info about the transaction
     */
    String getInfo();

    /**
     * Returns the price of the transaction.
     *
     * @return the price of the transaction
     */
    int getPrice();
}
