package switchtwentytwenty.project.interfaceadaptor.implcontroller.account;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import switchtwentytwenty.project.Application;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.aggregate.account.AccountFactory;
import switchtwentytwenty.project.domain.aggregate.account.CashAccount;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;
import switchtwentytwenty.project.interfaceadaptor.repository.AccountRepository;
import switchtwentytwenty.project.interfaceadaptor.repository.PersonRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc(addFilters = false)
class CheckAccountBalanceControllerIT {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PersonRepository personRepository;

    @BeforeEach
    public void before(){
        personRepository.deleteAll();
    }

    @Test
    @DisplayName("Check person cash account balance successfully")
    void testCheckAccountBalanceSuccessfully_PersonCashAccount()
            throws Exception {
        //arrange
        String holderID = "admin@gmail.com";
        String balance = "20.36";

        MoneyValue initialBalance = new MoneyValue(BigDecimal.valueOf(20.36));
        CashAccount cashAccount = (CashAccount) AccountFactory.createCashAccount(new AccountID(UUID.randomUUID()), new AccountDesignation("Account"),initialBalance);
        accountRepository.save(cashAccount);
        String accountID = cashAccount.getID().toString();

        List<String> telephones = new ArrayList<>();
        telephones.add("922658453");
        PersonVoDTO voPersonDTO = new PersonVoDTO(
                new PersonName("Joaquina"),
                new BirthDate("2020-03-02"),
                new VAT("123456789"),
                new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal"),
                new TelephoneNumberList(telephones),
                new Email(holderID),
                new FamilyID(UUID.randomUUID()),
                new LedgerID(UUID.randomUUID()));

        Person member = PersonFactory.create(voPersonDTO);
        member.addAccountID(cashAccount.getID());
        personRepository.save(member);

        //act-assert
        this.mockMvc.perform(get("/members/" + holderID + "/accounts/" + accountID + "/balance"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(balance)));
    }

    @Test
    @DisplayName("Check current account balance successfully")
    void testCheckAccountBalanceSuccessfully_CurrentAccount() throws Exception {
        //arrange
        String holderID = "admin@gmail.com";

        Account currentAccount = AccountFactory.createBankAccount(new AccountID(UUID.randomUUID()), new AccountDesignation("Account"), Constants.CURRENT_ACCOUNT_TYPE);
        accountRepository.save(currentAccount);
        String accountID = currentAccount.getID().toString();

        List<String> telephones = new ArrayList<>();
        telephones.add("922658453");
        PersonVoDTO voPersonDTO = new PersonVoDTO(
                new PersonName("Joaquina"),
                new BirthDate("2020-03-02"),
                new VAT("123456789"),
                new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal"),
                new TelephoneNumberList(telephones),
                new Email(holderID),
                new FamilyID(UUID.randomUUID()),
                new LedgerID(UUID.randomUUID()));
        Person member = PersonFactory.create(voPersonDTO);
        member.addAccountID(currentAccount.getID());
        personRepository.save(member);

        //act-assert
        this.mockMvc.perform(get("/members/" + holderID + "/accounts/" + accountID + "/balance"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("0")));
    }

    @Test
    @DisplayName("Check bank savings account balance successfully")
    void testCheckAccountBalanceSuccessfully_BankSavingAccount() throws Exception {
        //arrange
        String holderID = "admin@gmail.com";

        Account currentAccount = AccountFactory.createBankAccount(new AccountID(UUID.randomUUID()), new AccountDesignation("Account"), Constants.CURRENT_ACCOUNT_TYPE);
        accountRepository.save(currentAccount);
        String accountID = currentAccount.getID().toString();

        List<String> telephones = new ArrayList<>();
        telephones.add("922658453");
        PersonVoDTO voPersonDTO = new PersonVoDTO(
                new PersonName("Joaquina"),
                new BirthDate("2020-03-02"),
                new VAT("123456789"),
                new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal"),
                new TelephoneNumberList(telephones),
                new Email(holderID),
                new FamilyID(UUID.randomUUID()),
                new LedgerID(UUID.randomUUID()));
        Person member = PersonFactory.create(voPersonDTO);
        member.addAccountID(currentAccount.getID());
        personRepository.save(member);

        //act-assert
        this.mockMvc.perform(get("/members/" + holderID + "/accounts/" + accountID + "/balance"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("0")));
    }

    @Test
    @DisplayName("Check credit account balance successfully")
    void testCheckAccountBalanceSuccessfully_CreditAccount() throws Exception {
        //arrange
        String holderID = "admin@gmail.com";

        Account currentAccount = AccountFactory.createBankAccount(new AccountID(UUID.randomUUID()), new AccountDesignation("Account"), Constants.CURRENT_ACCOUNT_TYPE);
        accountRepository.save(currentAccount);
        String accountID = currentAccount.getID().toString();

        List<String> telephones = new ArrayList<>();
        telephones.add("922658453");
        PersonVoDTO voPersonDTO = new PersonVoDTO(
                new PersonName("Joaquina"),
                new BirthDate("2020-03-02"),
                new VAT("123456789"),
                new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal"),
                new TelephoneNumberList(telephones),
                new Email(holderID),
                new FamilyID(UUID.randomUUID()),
                new LedgerID(UUID.randomUUID()));
        Person member = PersonFactory.create(voPersonDTO);
        member.addAccountID(currentAccount.getID());
        personRepository.save(member);

        //act-assert
        this.mockMvc.perform(get("/members/" + holderID + "/accounts/" + accountID + "/balance"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("0")));
    }

    @Test
    @DisplayName("Failure check account balance: holder not found")
    void failureCheckAccountBalance_HolderNotFound() throws Exception {
        //arrange
        String holderID = "admin@gmail.com";

        Account currentAccount = AccountFactory.createBankAccount(new AccountID(UUID.randomUUID()), new AccountDesignation("Account"), Constants.CURRENT_ACCOUNT_TYPE);
        accountRepository.save(currentAccount);
        String accountID = currentAccount.getID().toString();

        //act-assert
        this.mockMvc.perform(get("/members/" + holderID + "/accounts/" + accountID + "/balance"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Person not found")));
    }

    @Test
    @DisplayName("Failure check account balance: not owns account")
    void failureCheckAccountBalance_NotOwnsAccount() throws Exception {
        //arrange
        String notHolderID = "email@gmail.com";

        Account currentAccount = AccountFactory.createBankAccount(new AccountID(UUID.randomUUID()), new AccountDesignation("Account"), Constants.CURRENT_ACCOUNT_TYPE);
        accountRepository.save(currentAccount);
        String accountID = currentAccount.getID().toString();

        List<String> telephones = new ArrayList<>();
        telephones.add("922658453");
        PersonVoDTO voPersonDTO = new PersonVoDTO(
                new PersonName("Joaquina"),
                new BirthDate("2020-03-02"),
                new VAT("123456789"),
                new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal"),
                new TelephoneNumberList(telephones),
                new Email("email@gmail.com"),
                new FamilyID(UUID.randomUUID()),
                new LedgerID(UUID.randomUUID()));
        Person member = PersonFactory.create(voPersonDTO);
        personRepository.save(member);

        //act-assert
        this.mockMvc.perform(get("/members/" + notHolderID + "/accounts/" + accountID + "/balance"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Account does not belong to this holder")));
    }

    @Test
    @DisplayName("Failure check account balance: account not found")
    void failureCheckAccountBalance_AccountNotFound() throws Exception {
        //arrange
        String holderID = "admin@gmail.com";

        Account currentAccount = AccountFactory.createBankAccount(new AccountID(UUID.randomUUID()), new AccountDesignation("Account"), Constants.CURRENT_ACCOUNT_TYPE);
        String accountID = currentAccount.getID().toString();

        List<String> telephones = new ArrayList<>();
        telephones.add("922658453");
        PersonVoDTO voPersonDTO = new PersonVoDTO(
                new PersonName("Joaquina"),
                new BirthDate("2020-03-02"),
                new VAT("123456789"),
                new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal"),
                new TelephoneNumberList(telephones),
                new Email(holderID),
                new FamilyID(UUID.randomUUID()),
                new LedgerID(UUID.randomUUID()));
        Person member = PersonFactory.create(voPersonDTO);
        member.addAccountID(currentAccount.getID());
        personRepository.save(member);

        //act-assert
        this.mockMvc.perform(get("/members/" + holderID + "/accounts/" + accountID + "/balance"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Account not found")));
    }
}
