package cardealer.utils;

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

    static {
        URL url = NamesUtil.class.getResource(FILENAME);
        assert url != null;
        Path path;
        try {
            path = Paths.get(url.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        try (var lines = Files.lines(path)) {
            lines.forEach(NAMES::add);
        } catch (IOException e) {
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
