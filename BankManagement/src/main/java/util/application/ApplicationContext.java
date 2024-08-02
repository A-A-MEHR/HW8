package util.application;

import repository.UserRepository;
import repository.impl.*;
import service.*;
import service.Impl.*;

public class ApplicationContext {
    private static final ApplicationContext INSTANCE = new ApplicationContext();
    private static final UserService userService;
    private static final CreditCardService creditCardService;
    private static final BankService bankService;
    private static final TransActionService transActionService;
    private static final AccountService accountService;
    private static final Exchange exchange;

    private ApplicationContext() {
    }

    public static ApplicationContext getInstance() {
        return INSTANCE;
    }

    static {
        UserRepository userRepository = new UserRepositoryImpl();
        userService = (UserService) new UserServiceImpl(userRepository);
        TransActionRepositoryImpl transActionRepository = new TransActionRepositoryImpl();
        transActionService = (TransActionService) new TransActionServiceImpl(transActionRepository);
        CreditCardRepositoryImpl creditCardRepository = new CreditCardRepositoryImpl();
        creditCardService = (CreditCardService) new CreditCardServiceImpl(creditCardRepository);
        BankRepositoryImpl bankRepository = new BankRepositoryImpl();
        bankService = new BankServiceImpl(bankRepository);
        AccountRepositoryImpl accountRepository = new AccountRepositoryImpl();
        accountService = new AccountServiceImpl(accountRepository);
        exchange = new ExchangeImpl();


    }

    public UserService getUserService() {
        return userService;
    }

    public CreditCardService getCreditCardService() {
        return creditCardService;
    }

    public BankService getBankService() {
        return bankService;
    }

    public TransActionService getTransActionService() {
        return transActionService;
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public Exchange getExchangeService() {
        return exchange;
    }
}
