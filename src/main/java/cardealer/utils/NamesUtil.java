package cardealer.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
@SuppressWarnings("java:S112")
public final class NamesUtil {
    private static final List<String> NAMES = new ArrayList<>();
    private static final String FILENAME = "names.txt";
    private static final Logger LOGGER = LogManager.getLogger();

    static {
        URL url = NamesUtil.class.getResource(FILENAME);
        assert url != null;
        Path path = null;
        try {
            path = Paths.get(url.toURI());
        } catch (URISyntaxException e) {
            LOGGER.error("Error getting path to names file.", e);
        }

        assert path != null;
        try (var lines = Files.lines(path)) {
            lines.forEach(NAMES::add);
        } catch (IOException e) {
            LOGGER.error("Error reading names file.", e);
        }
    }

    private NamesUtil() {
        throw new UnsupportedOperationException("Utility class.");
    }

    public static String getRandomName() {
        return GetRandom.getRandomElement(NAMES);
    }
}
