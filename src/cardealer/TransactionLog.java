package cardealer;

import cardealer.transaction.ITransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class TransactionLog {
    private static final String NAME_HEADER = "Name";
    private static final String INFO_HEADER = "Info";
    private static final String PRICE_HEADER = "Price";
    private final List<ITransaction> transactionsList = new ArrayList<>();
    private int maxBuyerNameLength = NAME_HEADER.length();
    private int maxInfoLength = INFO_HEADER.length();
    private int maxPriceLength = PRICE_HEADER.length();

    @SuppressWarnings("unused")
    public List<ITransaction> getTransactionsList() {
        return transactionsList;
    }

    public void addTransaction(ITransaction transaction) {
        transactionsList.add(transaction);
        maxBuyerNameLength = Math.max(maxBuyerNameLength, transaction.getBuyerName().length());
        maxInfoLength = Math.max(maxInfoLength, transaction.getInfo().length());
        maxPriceLength = Math.max(maxPriceLength, String.valueOf(transaction.getPrice()).length());
    }

    public void printTransactions() {
        int maxNumberLength = String.valueOf(transactionsList.size()).length();
        var headerPaddingLength = maxNumberLength + 1;
        var format = "%-" + headerPaddingLength + "s %-" + maxBuyerNameLength + "s | %-" + maxInfoLength + "s | %" + maxPriceLength + "s%n";

        System.out.printf(format, " ", NAME_HEADER, INFO_HEADER, PRICE_HEADER);

        for (int num = 0; num < transactionsList.size(); num++) {
            var transaction = transactionsList.get(num);
            var buyerName = transaction.getBuyerName();
            var info = transaction.getInfo();
            var price = transaction.getPrice();
            var numStr = num + 1 + ".";
            var transactionString = String.format(format, numStr, buyerName, info, price);

            System.out.print(transactionString);
        }

    }

}
