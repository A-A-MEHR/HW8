package entity;

public class Account {
    private Integer id;
    private Integer accountNumber;
    private Integer credit;
    private Integer userId;
    private Integer shabaNumber;


    private String bankName;

    public Account(Integer accountNumber, Integer credit, Integer userId, String bankName, Integer shabaNumber) {
        this.accountNumber = accountNumber;
        this.credit = credit;
        this.userId = userId;
        this.bankName = bankName;
        this.shabaNumber = shabaNumber;
    }

    public Account(Integer id, Integer accountNumber, Integer credit, Integer userId, Integer shabaNumber, String bankName) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.credit = credit;
        this.userId = userId;
        this.shabaNumber = shabaNumber;
        this.bankName = bankName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getShabaNumber() {
        return shabaNumber;
    }

    public void setShabaNumber(Integer shabaNumber) {
        this.shabaNumber = shabaNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber=" + accountNumber +
                ", credit=" + credit +
                ", user=" + userId +
                '}';
    }
}
