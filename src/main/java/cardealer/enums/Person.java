package cardealer.enums;

import org.apache.logging.log4j.Logger;

/**
 * @author Moussa
 */
public enum Person {
    ALICE("Alice", "Smith"),
    BOB("Bob", "Baker"),
    CHARLIE("Charlie", "Brown");
    private static final Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(Person.class);
    private String firstName;
    private String lastName;

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void introduce() {
        LOGGER.info("Hi, my name is {} {}.", firstName, lastName);
    }
}
