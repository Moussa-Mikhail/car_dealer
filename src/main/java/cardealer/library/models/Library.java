package cardealer.library.models;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
@XmlRootElement(name = "library", namespace = "library.xsd")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"books"})
public class Library {
    private List<Book> books = new ArrayList<>();
//    @XmlElementWrapper(name = "members")
//    @XmlElement(name = "member")
//    private List<Member> members = new ArrayList<>();
//    @XmlElementWrapper(name = "employees")
//    @XmlElement(name = "employee")
//    private List<Employee> employees = new ArrayList<>();
//    @XmlElementWrapper(name = "checkouts")
//    @XmlElement(name = "checkout")
//    private List<Checkout> checkouts = new ArrayList<>();
//    @XmlElementWrapper(name = "returnStatuses")
//    @XmlElement(name = "returnStatus")
//    private List<ReturnStatus> returnStatuses = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book")
    public void setBooks(List<Book> books) {
        this.books = books;
    }

//    public List<Member> getMembers() {
//        return members;
//    }
//
//    public void setMembers(List<Member> members) {
//        this.members = members;
//    }
//
//    public List<Employee> getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(List<Employee> employees) {
//        this.employees = employees;
//    }
//
//    public List<Checkout> getCheckouts() {
//        return checkouts;
//    }
//
//    public void setCheckouts(List<Checkout> checkouts) {
//        this.checkouts = checkouts;
//    }
//
//    public List<ReturnStatus> getReturnStatuses() {
//        return returnStatuses;
//    }
//
//    public void setReturnStatuses(List<ReturnStatus> returnStatuses) {
//        this.returnStatuses = returnStatuses;
//    }
}
