package cardealer;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Moussa
 */
@SuppressWarnings("java:S112")
public class Name {

    private static final List<String> NAMES = new ArrayList<>();
    private static final String FILENAME = "names.txt";


    static {

        URL url = Name.class.getResource(FILENAME);
        assert url != null;

        File file;
        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        final Scanner scanner;

        try {

            scanner = new Scanner(file);

        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);
        }

        while (scanner.hasNextLine()) {

            NAMES.add(scanner.nextLine());
        }

    }

    private Name() {
        throw new AssertionError("Utility class.");
    }

    public static String getRandomName() {

        return GetRandom.getRandomElement(NAMES);
    }
}
