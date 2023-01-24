package cardealer.library.models;

import java.math.BigDecimal;

/**
 * @author Moussa
 */
public class Fee implements IdGettable {
    private long id;
    private long memberId;
    private long feeTypeId;
    private BigDecimal amount;
    private boolean isPaid;

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

    public long getFeeTypeId() {
        return feeTypeId;
    }

    public void setFeeTypeId(long feeTypeId) {
        this.feeTypeId = feeTypeId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
