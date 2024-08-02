package entity;

public class CreditCard {
    private Integer id;
    private Integer cardNumber;
    private Integer account_id;
    private boolean isActive;
    private Integer shabaNumber;

    public CreditCard(Integer cardNumber, Integer account_id, boolean isActive, Integer shabaNumber) {
        this.cardNumber = cardNumber;
        this.account_id = account_id;
        this.isActive = isActive;
        this.shabaNumber = shabaNumber;

    }

    public CreditCard(Integer id, Integer cardNumber, Integer account_id, boolean isActive, Integer shabaNumber) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.account_id = account_id;
        this.isActive = isActive;
        this.shabaNumber = shabaNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Integer account_id) {
        this.account_id = account_id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Integer getShabaNumber() {
        return shabaNumber;
    }

    public void setShabaNumber(Integer shabaNumber) {
        this.shabaNumber = shabaNumber;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", cardNumber=" + cardNumber +
                ", account_id=" + account_id +
                ", isActive=" + isActive +
                '}';
    }
}
