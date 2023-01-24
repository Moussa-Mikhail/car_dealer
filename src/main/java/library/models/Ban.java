package library.models;

import java.sql.Date;

/**
 * @author Moussa
 */
public class Ban implements IdGettable {
    private long id;
    private long memberId;
    private Date bannedDate;
    private Date bannedUntilDate;

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

    public Date getBannedDate() {
        return bannedDate;
    }

    public void setBannedDate(Date bannedDate) {
        this.bannedDate = bannedDate;
    }

    public Date getBannedUntilDate() {
        return bannedUntilDate;
    }

    public void setBannedUntilDate(Date bannedUntilDate) {
        this.bannedUntilDate = bannedUntilDate;
    }
}
