package service.Impl;

import dto.AccountDTO;
import entity.Account;
import entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repository.AccountRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    void insertAccount() {
        AccountDTO dto = AccountDTO.builder().bankName("melli").credit(1000).shabaNumber(12345).accountNumber(6789).build();
        User amirreza = User.builder().build();
        given(accountRepository.insert(any(Account.class))).willAnswer((x)->x.getArgument(0, Account.class));

        Account insert = accountService.insert(amirreza,dto);
        assertNotNull(insert);

        ArgumentCaptor<Account> accountArgumentCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepository).insert(accountArgumentCaptor.capture());
        Account accountValue = accountArgumentCaptor.getValue();
        //assertEquals(insert,accountValue);
        assertThat(insert).isNotNull().isEqualTo(accountValue);
        assertEquals(accountValue.getAccountNumber(), dto.getAccountNumber());
        assertEquals(accountValue.getShabaNumber(), dto.getShabaNumber());
        assertEquals(accountValue.getCredit(), dto.getCredit());
        assertEquals(accountValue.getBank().getName(), dto.getBankName());

    }
}