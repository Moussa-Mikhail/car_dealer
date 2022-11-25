package cardealer;

import cardealer.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class TransactionLog {

    private final List<Transaction> transactionsList = new ArrayList<>();
    private int maxBuyerNameLength = 1;
    private int maxInfoLength = 1;
    private int maxPriceLength = 1;

    @SuppressWarnings("unused")
    public List<Transaction> getTransactionsList() {
        return transactionsList;
    }

    public void addTransaction(Transaction transaction) {
        transactionsList.add(transaction);
        maxBuyerNameLength = Math.max(maxBuyerNameLength, transaction.getBuyerName().length());
        maxInfoLength = Math.max(maxInfoLength, transaction.getInfo().length());
        maxPriceLength = Math.max(maxPriceLength, String.valueOf(transaction.getPrice()).length());
    }

    public void printTransactions() {
        int maxNumberLength = String.valueOf(transactionsList.size()).length();
        var headerPaddingLength = maxNumberLength + 1;
        var format = "%-" + headerPaddingLength + "s %-" + maxBuyerNameLength + "s | %-" + maxInfoLength + "s | %" + maxPriceLength + "s%n";

        System.out.printf(format, " ", "Buyer", "Info", "Price");

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
