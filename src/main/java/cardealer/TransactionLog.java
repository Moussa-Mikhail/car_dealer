package cardealer;

import cardealer.transaction.ITransaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class TransactionLog {
    private static final String NAME_HEADER = "Name";
    private static final String DESCRIPTION_HEADER = "Description";
    private static final String PRICE_HEADER = "Price";
    private static final Logger LOGGER = LogManager.getLogger(TransactionLog.class);
    private final List<ITransaction> transactionsList = new ArrayList<>();
    private int maxBuyerNameLength = NAME_HEADER.length();
    private int maxInfoLength = DESCRIPTION_HEADER.length();
    private int maxPriceLength = PRICE_HEADER.length();

    public TransactionLog() {
        LOGGER.info("TransactionLog created.");
    }

    public void addTransaction(@NotNull ITransaction transaction) {
        transactionsList.add(transaction);
        maxBuyerNameLength = Math.max(maxBuyerNameLength, transaction.getBuyerName().length());
        maxInfoLength = Math.max(maxInfoLength, transaction.getDescription().length());
        maxPriceLength = Math.max(maxPriceLength, String.valueOf(transaction.getPrice()).length());
        LOGGER.info("Transaction added to log: {}", transaction);
    }

    public void printTransactions() {
        int maxNumberLength = String.valueOf(transactionsList.size()).length();
        int headerPaddingLength = maxNumberLength + 1;
        String format = "%-" + headerPaddingLength + "s %-" + maxBuyerNameLength + "s | %-" + maxInfoLength + "s | %" + maxPriceLength + "s%n";
        System.out.printf(format, " ", NAME_HEADER, DESCRIPTION_HEADER, PRICE_HEADER);
        for (int num = 0; num < transactionsList.size(); num++) {
            ITransaction transaction = transactionsList.get(num);
            String buyerName = transaction.getBuyerName();
            String description = transaction.getDescription();
            int price = transaction.getPrice();
            String numStr = num + 1 + ".";
            String transactionString = String.format(format, numStr, buyerName, description, price);
            System.out.print(transactionString);
        }
        LOGGER.info("Transactions printed.");
    }
}
