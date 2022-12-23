package cardealer;

import cardealer.enums.Color;
import cardealer.enums.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author Moussa
 */
public class Streams {
    private static final Logger LOGGER = LogManager.getLogger(Streams.class);

    public static void main(String[] args) {
        LOGGER.info("Colors in enum Color:");
        Arrays.stream(Color.values())
                .map(Color::toString).forEach(c -> LOGGER.info("{}", c));

        Arrays.stream(Person.values())
                .forEach(Person::introduce);

        IntStream.range(0, 11)
                .map(i -> i * i).filter(i -> i % 2 == 0).reduce(Integer::sum)
                .ifPresent(num -> LOGGER.info("The sum of only the even squares of ints between 1 and 10 inclusive : {}", num));

        // Please note that I have a few other examples of streams throughout the project.
    }
}
