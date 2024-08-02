package entity;

import util.enums.Status;
import util.enums.Success;

import java.time.LocalDate;
import java.time.LocalTime;

public class TransAction {
    private Integer id;

    private Status status;
    private LocalDate date;
    private LocalTime time;
    private Integer amount;
    private Integer credit_card_id;
    private Success success;


    public TransAction(Status status, Integer amount, Integer credit_card_id, Success success) {
        this.status = status;
        this.amount = amount;
        this.credit_card_id = credit_card_id;
        this.success = success;
        this.time = LocalTime.now();
        this.date = LocalDate.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }


    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getCredit_card_id() {
        return credit_card_id;
    }

    public void setCredit_card_id(Integer credit_card_id) {
        this.credit_card_id = credit_card_id;
    }

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "TransAction{" +
                "id=" + id +
                ", date=" + date +
                ", time=" + time +
                ", amount=" + amount +
                ", credit_card_id=" + credit_card_id +
                '}';
    }
}
