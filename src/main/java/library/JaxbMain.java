package library;

import library.models.Book;
import library.models.Library;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * @author Moussa
 */
public class JaxbMain {
    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(Library.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Library library = new Library();
            Book book = new Book();
            book.setId(1);
            book.setTitle("The Hobbit");
            book.setAuthorId(1);
            book.setGenreId(1);
            book.setIsbn("123456789");
            book.setQuantity(1);
            library.getBooks().add(book);
            marshaller.marshal(library, new File("library_out.xml"));

            Unmarshaller unmarshaller = context.createUnmarshaller();
            library = (Library) unmarshaller.unmarshal(JaxbMain.class.getResource("/library.xml"));
            System.out.println(library.getBooks());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
