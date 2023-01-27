package library.models;

import library.DateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Date;

/**
 * @author Moussa
 */
@XmlRootElement(name = "checkout")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "memberId", "bookId", "employeeId", "returnStatusId", "checkoutDate", "dueDate", "returnedDate"})
public class Checkout implements IdGettable {
    private long id;
    private long memberId;
    private long bookId;
    private long employeeId;
    private long returnStatusId;
    private Date checkoutDate;
    private Date dueDate;
    private Date returnedDate;

    @Override
    public String toString() {
        return "Checkout{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", bookId=" + bookId +
                ", employeeId=" + employeeId +
                ", returnStatusId=" + returnStatusId +
                ", checkoutDate=" + checkoutDate +
                ", dueDate=" + dueDate +
                ", returnedDate=" + returnedDate +
                '}';
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getReturnStatusId() {
        return returnStatusId;
    }

    public void setReturnStatusId(long returnStatusId) {
        this.returnStatusId = returnStatusId;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }
}
