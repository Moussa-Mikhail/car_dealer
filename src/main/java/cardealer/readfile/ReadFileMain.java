package cardealer.readfile;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Moussa
 */
public class ReadFileMain {
    public static final String RESULT_FORMAT = "Number of unique words:";
    private static final String FILENAME = "words.txt";
    private static final Logger LOGGER = LogManager.getLogger(ReadFileMain.class);

    public static void main(String[] args) {
        File file = new File(FILENAME);

        List<String> words = null;
        try {
            words = FileUtils.readLines(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.fatal(e.getMessage());
            System.out.println(e.getMessage());
            System.exit(1);
        }

        String lastLine = words.get(words.size() - 1);
        if (lastLine.startsWith(RESULT_FORMAT)) {
            words.remove(words.size() - 1);
        }

        Set<String> uniqueWords = new HashSet<>(words);
        String result = String.format("Number of unique words: %d.", uniqueWords.size());

        words.add(result);

        file = new File(FILENAME);
        try {
            FileUtils.writeLines(file, words);
        } catch (IOException e) {
            LOGGER.fatal(e.getMessage());
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
