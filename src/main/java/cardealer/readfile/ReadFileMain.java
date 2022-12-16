package cardealer.readfile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author Moussa
 */
public class ReadFileMain {
    public static final String RESULT_PREFIX = "Number of unique words:";
    private static final String FILENAME = "words.txt";
    private static final Logger LOGGER = LogManager.getLogger(ReadFileMain.class);

    public static void main(String[] args) {
        File file = new File(FILENAME);

        List<String> lines = null;
        try {
            lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            LOGGER.fatal(e.getMessage());
            System.out.println(e.getMessage());
            System.exit(1);
        }

        // Remove result message if it exists.
        String lastLine = lines.get(lines.size() - 1);
        if (lastLine.startsWith(RESULT_PREFIX)) {
            lines.remove(lines.size() - 1);
        }

        List<String> words = new ArrayList<>();
        for (String line : lines) {
            String[] split = StringUtils.split(line);
            // Remove punctuation.
            Arrays.stream(split).map(s -> StringUtils.replaceChars(s, ",.", "")).forEach(words::add);
        }

        System.out.println("Number of words: " + words.size());

        int numUniqueWords = new HashSet<>(words).size();
        String result = String.format(RESULT_PREFIX + " %d", numUniqueWords);
        System.out.println(result);

        lines.add(result);

        file = new File(FILENAME);
        try {
            FileUtils.writeLines(file, lines);
        } catch (IOException e) {
            LOGGER.fatal(e.getMessage());
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
