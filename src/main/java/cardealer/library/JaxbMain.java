package cardealer.library;

import cardealer.library.models.Library;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * @author Moussa
 */
public class JaxbMain {
    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(Library.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Library library = (Library) unmarshaller.unmarshal(JaxbMain.class.getResource("/library.xml"));
            System.out.println(library.getBooks());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
