package cardealer.enums;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Moussa
 */
@SuppressWarnings("NonFinalFieldInEnum")
public enum CarModel {
    AUDI(Make.AUDI, "A4", 2015, 20000),
    BMW(Make.BMW, "X5", 2016, 25000),
    MERCEDES(Make.MERCEDES, "C200", 2017, 30000);
    static final Logger LOGGER = LogManager.getLogger(CarModel.class);
    private Make make;
    private String model;
    private int year;
    private int price;

    CarModel(Make make, String model, int year, int price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public void printInfo() {
        LOGGER.info("{}", this);
    }

    @Override
    public String toString() {
        return String.format("Make: %s, Model: %s, Year: %d, Price: %d", make, model, year, price);
    }
}
