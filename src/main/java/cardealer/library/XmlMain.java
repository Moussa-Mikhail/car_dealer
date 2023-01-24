package cardealer.library;

import cardealer.library.models.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
public class XmlMain {
    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
    private static final List<Book> BOOKS = new ArrayList<>();
    private static final List<Member> MEMBERS = new ArrayList<>();
    private static final List<Employee> EMPLOYEES = new ArrayList<>();
    private static final List<ReturnStatus> RETURN_STATUSES = new ArrayList<>();
    private static final List<Checkout> CHECKOUTS = new ArrayList<>();

    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
        DocumentBuilder builder;
        Document doc;

        try {
            builder = dbf.newDocumentBuilder();
            doc = builder.parse(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\library.xml"));

            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory factory = SchemaFactory.newInstance(language);
            Schema schema = factory.newSchema(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\library.xsd"));

            Validator validator = schema.newValidator();

            validator.validate(new DOMSource(doc));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        readXml(doc, "book");
        for (Book book : BOOKS) {
            System.out.println(book);
        }

        readXml(doc, "member");
        for (Member member : MEMBERS) {
            System.out.println(member);
        }

        readXml(doc, "employee");
        for (Employee employee : EMPLOYEES) {
            System.out.println(employee);
        }

        readXml(doc, "returnStatus");
        for (ReturnStatus returnStatus : RETURN_STATUSES) {
            System.out.println(returnStatus);
        }

        readXml(doc, "checkout");
        for (Checkout checkout : CHECKOUTS) {
            System.out.println(checkout);
        }
    }

    private static void readXml(Document doc, String entity) {
        NodeList nodes = null;
        if (doc != null) {
            nodes = doc.getElementsByTagName(entity);
        }

        int length = 0;
        if (nodes != null) {
            length = nodes.getLength();
        }

        for (int i = 0; i < length; i++) {
            Node node = nodes.item(i);
            Element element = (Element) node;
            switch (entity) {
                case "book":
                    Book book = new Book();
                    book.setId(Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()));
                    book.setTitle(element.getElementsByTagName("title").item(0).getTextContent());
                    book.setAuthorId(Long.parseLong(element.getElementsByTagName("authorId").item(0).getTextContent()));
                    book.setGenreId(Long.parseLong(element.getElementsByTagName("genreId").item(0).getTextContent()));
                    book.setIsbn(element.getElementsByTagName("isbn").item(0).getTextContent());
                    book.setQuantity(Integer.parseInt(element.getElementsByTagName("quantity").item(0)
                            .getTextContent()));

                    BOOKS.add(book);
                    break;

                case "member":
                    Member member = new Member();
                    member.setId(Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()));
                    member.setFirstName(element.getElementsByTagName("firstName").item(0).getTextContent());
                    member.setLastName(element.getElementsByTagName("lastName").item(0).getTextContent());
                    member.setJoinedDate(Date.valueOf(element.getElementsByTagName("joinedDate").item(0)
                            .getTextContent()));

                    MEMBERS.add(member);
                    break;

                case "employee":
                    Employee employee = new Employee();
                    employee.setId(Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()));
                    employee.setFirstName(element.getElementsByTagName("firstName").item(0).getTextContent());
                    employee.setLastName(element.getElementsByTagName("lastName").item(0).getTextContent());
                    employee.setSalary(Integer.parseInt(element.getElementsByTagName("salary").item(0)
                            .getTextContent()));
                    employee.setHiredDate(Date.valueOf(element.getElementsByTagName("hiredDate").item(0)
                            .getTextContent()));

                    EMPLOYEES.add(employee);
                    break;

                case "returnStatus":
                    ReturnStatus returnStatus = new ReturnStatus();
                    returnStatus.setId(Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()));
                    returnStatus.setDescription(element.getElementsByTagName("description").item(0).getTextContent());

                    RETURN_STATUSES.add(returnStatus);
                    break;

                case "checkout":
                    Checkout checkout = new Checkout();
                    checkout.setId(Long.parseLong(element.getElementsByTagName("id").item(0).getTextContent()));
                    checkout.setMemberId(Long.parseLong(element.getElementsByTagName("memberId").item(0)
                            .getTextContent()));
                    checkout.setBookId(Long.parseLong(element.getElementsByTagName("bookId").item(0).getTextContent()));
                    checkout.setEmployeeId(Long.parseLong(element.getElementsByTagName("employeeId").item(0)
                            .getTextContent()));
                    checkout.setReturnStatusId(Long.parseLong(element.getElementsByTagName("returnStatusId").item(0)
                            .getTextContent()));
                    checkout.setCheckoutDate(Date.valueOf(element.getElementsByTagName("checkoutDate").item(0)
                            .getTextContent()));
                    checkout.setDueDate(Date.valueOf(element.getElementsByTagName("dueDate").item(0).getTextContent()));
                    checkout.setReturnedDate(Date.valueOf(element.getElementsByTagName("returnedDate").item(0)
                            .getTextContent()));

                    CHECKOUTS.add(checkout);
                    break;

                default:
                    break;
            }
        }
    }
}
