package dto;

import entity.Bank;
import entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class AccountDTO {
    private int accountNumber;
    private int credit;
    private String bankName;
    private int shabaNumber;
}
