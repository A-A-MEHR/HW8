package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Table(name = "accounts")
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer accountNumber;
    private Integer credit;
    @ManyToOne
    private User user;
    private Integer shabaNumber;
    @ManyToOne
    private Bank bank;

    public Account(Integer accountNumber, int credit, User token, Bank bank, Integer shabaNumber) {
        this.accountNumber = accountNumber;
        this.credit = credit;
        this.user = token;
        this.bank = bank;
        this.shabaNumber = shabaNumber;
    }
}

