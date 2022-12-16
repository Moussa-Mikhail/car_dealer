package cardealer.utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
@SuppressWarnings("java:S112")
public final class NamesUtil {
    private static final List<String> NAMES = new ArrayList<>();
    private static final String FILENAME = "names.txt";
    private static final Logger LOGGER = LogManager.getLogger(NamesUtil.class);

    static {
        ClassLoader classLoader = NamesUtil.class.getClassLoader();
        InputStream fileStream = classLoader.getResourceAsStream(FILENAME);
        if (fileStream == null) {
            String message = "File not found: " + FILENAME;
            LOGGER.fatal(message);
            System.out.println(message);
            System.exit(1);
        }

        List<String> names = null;
        File file = null;
        try {
            file = File.createTempFile("temp", ".txt");
            FileUtils.copyInputStreamToFile(fileStream, file);
        } catch (IOException e) {
            LOGGER.fatal(e.getMessage());
            System.out.println(e.getMessage());
            System.exit(1);
        }

        try {
            names = FileUtils.readLines(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.fatal(e.getMessage());
            System.out.println(e.getMessage());
            System.exit(1);
        }

        NAMES.addAll(names);
    }

    private NamesUtil() {
        throw new UnsupportedOperationException("Utility class.");
    }

    public static String getRandomName() {
        return GetRandom.getRandomElement(NAMES);
    }
}
