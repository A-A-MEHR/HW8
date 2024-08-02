package entity;

import jakarta.persistence.*;
import lombok.*;
import util.enums.Status;
import util.enums.Success;

import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
@Builder
@ToString
@Getter
@Setter
public class TransAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Status status;
    private LocalDate date;
    private LocalTime time;
    private Integer amount;

    public TransAction( Success success,Integer amount, CreditCard creditCard,Status status) {
        this.success = success;
        this.creditCard = creditCard;
        this.amount = amount;
        this.status = status;
    }

    @ManyToOne
    private CreditCard creditCard;
    private Success success;
}
