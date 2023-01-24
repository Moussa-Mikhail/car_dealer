package cardealer.library.models;

import javax.xml.bind.annotation.XmlElement;
import java.sql.Date;

/**
 * @author Moussa
 */
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

    @XmlElement(name = "id")
    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @XmlElement(name = "memberId")
    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    @XmlElement(name = "bookId")
    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    @XmlElement(name = "employeeId")
    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    @XmlElement(name = "returnStatusId")
    public long getReturnStatusId() {
        return returnStatusId;
    }

    public void setReturnStatusId(long returnStatusId) {
        this.returnStatusId = returnStatusId;
    }

    @XmlElement(name = "checkoutDate")
    public Date getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(Date checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    @XmlElement(name = "dueDate")
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @XmlElement(name = "returnedDate")
    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }
}
