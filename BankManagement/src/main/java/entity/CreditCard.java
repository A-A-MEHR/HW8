package entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Builder
@Table(name = "credit_card")
@Getter
@Setter
@AllArgsConstructor
@ToString
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer cardNumber;
    @OneToOne
    private Account account;
    private boolean isActive;
    private Integer shabaNumber;

    public CreditCard(Integer cardNumber, Account account, boolean isActive, Integer shabaNumber) {
        this.cardNumber = cardNumber;
        this.account = account;
        this.isActive = isActive;
        this.shabaNumber = shabaNumber;
    }
}
