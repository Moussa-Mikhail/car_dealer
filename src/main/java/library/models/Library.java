package library.models;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Moussa
 */
@XmlRootElement(name = "library")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"books", "members", "employees", "checkouts", "returnStatuses"})
public class Library {
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private List<Checkout> checkouts = new ArrayList<>();
    private List<ReturnStatus> returnStatuses = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book")
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Member> getMembers() {
        return members;
    }

    @XmlElementWrapper(name = "members")
    @XmlElement(name = "member")
    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Checkout> getCheckouts() {
        return checkouts;
    }

    @XmlElementWrapper(name = "checkouts")
    @XmlElement(name = "checkout")
    public void setCheckouts(List<Checkout> checkouts) {
        this.checkouts = checkouts;
    }

    public List<ReturnStatus> getReturnStatuses() {
        return returnStatuses;
    }

    @XmlElementWrapper(name = "returnStatuses")
    @XmlElement(name = "returnStatus")
    public void setReturnStatuses(List<ReturnStatus> returnStatuses) {
        this.returnStatuses = returnStatuses;
    }
}
