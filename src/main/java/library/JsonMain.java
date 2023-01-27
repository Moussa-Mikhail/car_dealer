package library;

import com.fasterxml.jackson.databind.ObjectMapper;
import library.models.Book;
import library.models.Library;
import library.models.Member;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.TimeZone;

/**
 * @author Moussa
 */
public class JsonMain {
    private static final Logger LOGGER = LogManager.getLogger(JsonMain.class);

    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Jackson parses dates as being one day before the actual date
            // Setting the timezone to the default timezone fixes this issue
            objectMapper.setTimeZone(TimeZone.getDefault());
            Library library = objectMapper.readValue(JsonMain.class.getResource("/library.json"), Library.class);
            for (Book book : library.getBooks()) {
                System.out.println(book);
            }

            for (Member member : library.getMembers()) {
                System.out.println(member);
            }

            Book newBook = new Book();
            newBook.setId(3);
            newBook.setTitle("New Book");
            newBook.setAuthorId(2);
            newBook.setGenreId(2);
            newBook.setIsbn("123-45678-90112");
            newBook.setQuantity(4);
            library.getBooks().add(newBook);

            objectMapper.writeValue(new File("library_out.json"), library);
        } catch (IOException e) {
            LOGGER.error("Error while reading json file", e);
        }
    }
}
