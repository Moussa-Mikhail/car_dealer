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

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Library library = (Library) unmarshaller.unmarshal(JaxbMain.class.getResource("/library.xml"));
            for (Book book : library.getBooks()) {
                System.out.println(book);
            }
            System.out.println();

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Book newBook = new Book();
            newBook.setId(3);
            newBook.setTitle("The Hobbit");
            newBook.setAuthorId(2);
            newBook.setGenreId(2);
            newBook.setIsbn("123-45678-90112");
            newBook.setQuantity(4);
            library.getBooks().add(newBook);

            for (Book book : library.getBooks()) {
                System.out.println(book);
            }
            marshaller.marshal(library, new File("library_out.xml"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
