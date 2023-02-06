package library.patterns;

import library.models.Book;
import library.sql.IBookDAO;
import library.sql.mybatis.BookDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Moussa
 */
public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            BookBuilder bookBuilder = new BookBuilder();
            Book book = bookBuilder
                    .setTitle("Example Book")
                    .setAuthorId(3)
                    .setGenreId(1)
                    .setIsbn("123456789")
                    .setQuantity(1)
                    .build();

            IBookDAO bookDAO = new BookDAO();
            bookDAO.createEntity(book);

            // See library.sql.jdbc.AbstractDAO.executeCommand for an example of the strategy pattern.

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
