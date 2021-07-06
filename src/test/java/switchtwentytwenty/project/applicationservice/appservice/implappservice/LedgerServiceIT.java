package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentytwenty.project.applicationservice.irepository.IAccountRepository;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.applicationservice.irepository.ILedgerRepository;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.autentication.UserRepository;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.aggregate.account.AccountFactory;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.designation.TransactionDescription;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.domain.share.id.CategoryID;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.transactiondata.*;
import switchtwentytwenty.project.dto.outdto.MovementOutDTO;
import switchtwentytwenty.project.dto.todomaindto.TransferInformationDTO;
import switchtwentytwenty.project.dto.toservicedto.FamilyAndAdminDTO;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LedgerServiceIT {

    @Autowired
    LedgerService ledgerService;

    @Autowired
    AccountService accountService;

    @Autowired
    ILedgerRepository ledgerRepository;
    @Autowired
    IAccountRepository accountRepository;
    @Autowired
    FamilyAndMemberService familyAndMemberService;
    @Autowired
    IFamilyRepository familyRepository;
    @Autowired
    IPersonRepository personRepository;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void before() {
        personRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    @DisplayName("Get list of movements between dates")
    void getListOfMovementsBetweenDates() throws Exception {
        //arrange
        int result;
        int expected = 2;

        //create family and administrator
        String adminEmail = "margaret_hamilton@gmail.com";
        Email adminID = new Email(adminEmail);
        String username = "Maggy";

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "213025086", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", phoneNumbers, adminEmail, "Hamilton", username, "1234");
        familyAndMemberService.startFamily(dto);
        Person admin = personRepository.findByID(adminID);

        //create cash account for administrator
        MoneyValue amountValue = new MoneyValue(new BigDecimal(100));
        AccountDesignation accountDesignation = new AccountDesignation("My cash account");
        AccountID accountID = new AccountID(UUID.randomUUID());

        Account cashAccount = AccountFactory.createCashAccount(accountID, accountDesignation, amountValue);
        admin.addAccountID(accountID);
        personRepository.save(admin);
        this.accountRepository.save(cashAccount);

        //add movements
        TransactionDate betweenDateOne = new TransactionDate("2021-04-02");
        MoneyValue moneyValue = new MoneyValue(new BigDecimal("110"));
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());

        TransferInformationDTO transferDTO = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withOriginAccountID(accountID)
                .withDestinationAccountID(new AccountID(UUID.randomUUID()))
                .withCategory(categoryID)
                .withDate(betweenDateOne)
                .withCredit(new MovementType(Constants.CREDIT))
                .withDebit(new MovementType(Constants.DEBIT))
                .withDescription(new TransactionDescription("Cromos"))
                .withAmount(amountValue)
                .build();

        Transfer transferOne = new Transfer(transferDTO, moneyValue);

        TransactionDescription transactionDescription = new TransactionDescription("Hello");
        TransactionDate transactionDate = new TransactionDate("2021-05-01");
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(20000));
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2021-05-10 10:10:11"));
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(200));

        Payment payment = new Payment.PaymentBuilder()
                .withBaseTransaction(categoryID, transactionDescription, transactionDate, balance, systemDateEntry)
                .withDebitMovement(accountID, amount)
                .build();

        LedgerID ledgerID = admin.getLedgerID();
        Ledger ledger = ledgerRepository.findByID(ledgerID);

        ledger.addTransaction(transferOne);
        ledger.addTransaction(payment);

        ledgerRepository.save(ledger);

        //define dates
        String startDate = "2021-04-01";
        String endDate = "2021-05-13";

        //act
        List<MovementOutDTO> listOfMovementsBetweenDates = ledgerService.getListOfMovementsBetweenDates(username, accountID.toString(), startDate, endDate);
        result = listOfMovementsBetweenDates.size();

        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get list of movements between dates - empty list")
    void getListOfMovementsBetweenDatesEmptyList() throws Exception {
        //arrange
        int result;
        int expected = 0;

        //create family and administrator
        String adminEmail = "john_neumann@gmail.com";
        Email adminID = new Email(adminEmail);
        String username = "Maggy";

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "213025086", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", phoneNumbers, adminEmail, "Hamilton", username, "1234");
        familyAndMemberService.startFamily(dto);
        Person admin = personRepository.findByID(adminID);

        //create cash account for administrator
        MoneyValue amountValue = new MoneyValue(new BigDecimal(500));
        AccountDesignation accountDesignation = new AccountDesignation("My other cash account");
        AccountID accountID = new AccountID(UUID.randomUUID());

        Account cashAccount = AccountFactory.createCashAccount(accountID, accountDesignation, amountValue);
        admin.addAccountID(accountID);
        personRepository.save(admin);
        this.accountRepository.save(cashAccount);

        //add movements

        TransactionDate outsideDates = new TransactionDate("2021-04-02");
        MoneyValue moneyValue = new MoneyValue(new BigDecimal("110"));
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());

        TransferInformationDTO transferDTO = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withOriginAccountID(accountID)
                .withDestinationAccountID(new AccountID(UUID.randomUUID()))
                .withCategory(categoryID)
                .withDate(outsideDates)
                .withCredit(new MovementType(Constants.CREDIT))
                .withDebit(new MovementType(Constants.DEBIT))
                .withDescription(new TransactionDescription("Cromos"))
                .withAmount(amountValue)
                .build();

        Transfer transferOne = new Transfer(transferDTO, moneyValue);

        LedgerID ledgerID = admin.getLedgerID();
        Ledger ledger = ledgerRepository.findByID(ledgerID);

        ledger.addTransaction(transferOne);

        ledgerRepository.save(ledger);

        //define dates
        String startDate = "2021-05-01";
        String endDate = "2021-05-05";

        //act
        List<MovementOutDTO> listOfMovementsBetweenDates = ledgerService.getListOfMovementsBetweenDates(username, accountID.toString(), startDate, endDate);
        result = listOfMovementsBetweenDates.size();
        //assert
        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Get list of movements between dates - start and end dates wrong order")
    void getListOfMovementsBetweenDatesDatesWrongOrder() throws Exception {
        //arrange
        int result;
        int expected = 3;

        //create family and administrator
        String adminEmail = "hitch@gmail.com";
        Email adminID = new Email(adminEmail);
        String username = "Maggy";

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "213025086", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", phoneNumbers, adminEmail, "Hamilton", username, "1234");
        familyAndMemberService.startFamily(dto);
        Person admin = personRepository.findByID(adminID);

        //create cash account for administrator
        MoneyValue amountValue = new MoneyValue(new BigDecimal(100));
        AccountDesignation accountDesignation = new AccountDesignation("My cash account");
        AccountID accountID = new AccountID(UUID.randomUUID());

        Account cashAccount = AccountFactory.createCashAccount(accountID, accountDesignation, amountValue);
        admin.addAccountID(accountID);
        personRepository.save(admin);
        this.accountRepository.save(cashAccount);

        //add movements

        TransactionDate betweenDateOne = new TransactionDate("2021-04-02");
        MoneyValue moneyValue = new MoneyValue(new BigDecimal("110"));
        CategoryID categoryID = new CategoryID(UUID.randomUUID().toString());

        TransferInformationDTO transferDTO = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withOriginAccountID(accountID)
                .withDestinationAccountID(new AccountID(UUID.randomUUID()))
                .withCategory(categoryID)
                .withDate(betweenDateOne)
                .withCredit(new MovementType(Constants.CREDIT))
                .withDebit(new MovementType(Constants.DEBIT))
                .withDescription(new TransactionDescription("Cromos"))
                .withAmount(amountValue)
                .build();

        Transfer transferOne = new Transfer(transferDTO, moneyValue);

        TransferInformationDTO transferDTO2 = new TransferInformationDTO.TransferInformationDTOBuilder()
                .withOriginAccountID(accountID)
                .withDestinationAccountID(new AccountID(UUID.randomUUID()))
                .withCategory(categoryID)
                .withDate(betweenDateOne)
                .withCredit(new MovementType(Constants.CREDIT))
                .withDebit(new MovementType(Constants.DEBIT))
                .withDescription(new TransactionDescription("Cromos"))
                .withAmount(new MoneyValue(BigDecimal.valueOf(10)))
                .build();

        Transfer transferTwo = new Transfer(transferDTO2, moneyValue);

        TransactionDescription transactionDescription = new TransactionDescription("Hello");
        TransactionDate transactionDate = new TransactionDate("2021-05-01");
        MoneyValue balance = new MoneyValue(BigDecimal.valueOf(20000));
        SystemDateEntry systemDateEntry = new SystemDateEntry(new SimpleDateFormat(Constants.SYSTEM_ENTRY_DATE_FORMAT).parse("2021-05-10 10:10:11"));
        MoneyValue amount = new MoneyValue(BigDecimal.valueOf(200));

        Payment payment = new Payment.PaymentBuilder()
                .withBaseTransaction(categoryID, transactionDescription, transactionDate, balance, systemDateEntry)
                .withDebitMovement(accountID, amount)
                .build();

        LedgerID ledgerID = admin.getLedgerID();

        Ledger ledger = ledgerRepository.findByID(ledgerID);

        ledger.addTransaction(transferOne);
        ledger.addTransaction(transferTwo);
        ledger.addTransaction(payment);

        ledgerRepository.save(ledger);

        //define dates
        String startDate = "2021-04-01";
        String endDate = "2021-05-13";

        //act
        List<MovementOutDTO> dtoList = ledgerService.getListOfMovementsBetweenDates(username, accountID.toString(), endDate, startDate);
        result = dtoList.size();

        //assert
        assertEquals(result, expected);
    }
}