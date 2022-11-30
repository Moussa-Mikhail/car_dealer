package cardealer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
@SuppressWarnings("java:S112")
public class NamesUtil {

    private static final List<String> NAMES = new ArrayList<>();
    private static final String FILENAME = "names.txt";

    static {
        URL url = NamesUtil.class.getResource(FILENAME);
        assert url != null;

        // Copied and modified from
        // https://www.digitalocean.com/community/tutorials/java-read-file-line-by-line
        try {
            var path = Paths.get(url.toURI());
            NAMES.addAll(Files.readAllLines(path));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private NamesUtil() {
        throw new UnsupportedOperationException("Utility class.");
    }

    public static String getRandomName() {
        return GetRandom.getRandomElement(NAMES);
    }
}
