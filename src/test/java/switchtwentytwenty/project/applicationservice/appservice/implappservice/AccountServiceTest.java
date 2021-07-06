package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountIDGenerator;
import switchtwentytwenty.project.autentication.ERole;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.aggregate.account.CashAccount;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.id.*;
import switchtwentytwenty.project.domain.share.persondata.*;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.outdto.AccountOutDTO;
import switchtwentytwenty.project.dto.outdto.FamilyCashAccountOutDTO;
import switchtwentytwenty.project.dto.outdto.PersonalCashAccountOutDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;
import switchtwentytwenty.project.dto.toservicedto.FamilyAndAdminDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.repository.AccountRepository;
import switchtwentytwenty.project.interfaceadaptor.repository.FamilyRepository;
import switchtwentytwenty.project.interfaceadaptor.repository.PersonRepository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    AccountService service;
    @Mock
    AccountRepository accountRepository;
    @Mock
    PersonRepository personRepository;
    @Mock
    FamilyRepository familyRepository;
    @Mock
    Person person;
    @Mock
    Family family;
    @Mock
    IAccountIDGenerator accountDGenerator;

    @Test
    @DisplayName("Add current account successfully")
    void addCurrentAccountSuccessfully() throws Exception {
        //arrange
        String designation = "Account";
        String personIDString = "email@gmail.com";
        String accountType = "current";

        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        when(person.getAccountIDList()).thenReturn(new AccountIDList());
        doNothing().when(accountRepository).save(Mockito.any(Account.class));
        when(accountDGenerator.generate()).thenReturn(new AccountID(UUID.randomUUID()));
        doNothing().when(personRepository).save(person);
        //act - assert
        assertDoesNotThrow(() -> service.addBankAccount(designation, personIDString, accountType));
    }

    @Test
    @DisplayName("Add current account successfully: holder with others accounts")
    void addCurrentAccountSuccessfully_WithOthersAccounts() throws Exception {
        //arrange
        String designation = "Account";
        String personIDString = "email@gmail.com";
        String accountType = "current";

        CashAccount cashAccount = Mockito.mock(CashAccount.class);
        AccountIDList idList = Mockito.mock(AccountIDList.class);
        AccountID id = Mockito.mock(AccountID.class);
        idList.add(id);
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        when(person.getAccountIDList()).thenReturn(idList);
        when(idList.size()).thenReturn(1);
        when(idList.getID(0)).thenReturn(id);
        when(accountRepository.findByID(Mockito.any(AccountID.class))).thenReturn(cashAccount);
        when(accountDGenerator.generate()).thenReturn(new AccountID(UUID.randomUUID()));
        when(cashAccount.isSameDesignation(new AccountDesignation(designation))).thenReturn(false);
        doNothing().when(accountRepository).save(Mockito.any(Account.class));
        doNothing().when(personRepository).save(person);
        //act - assert
        assertDoesNotThrow(() -> service.addBankAccount(designation, personIDString, accountType));
    }

    @Test
    @DisplayName("Failure add current account: invalid email")
    void failureAddCurrentAccount_InvalidEmail() {
        //arrange
        String designation = "Account";
        String personIDString = "emailgmail.com";
        String accountType = "current";

        //act - assert
        assertThrows(InvalidEmailException.class, () -> service.addBankAccount(designation, personIDString, accountType));
    }

    @Test
    @DisplayName("Failure add current account: null email")
    void failureAddCurrentAccount_NullEmail() {
        //arrange
        String designation = "Account";
        String accountType = "current";
        //act - assert
        assertThrows(NullPointerException.class, () -> service.addBankAccount(designation, null, accountType));
    }

    @Test
    @DisplayName("Failure add current account: invalid designation")
    void failureAddCurrentAccount_InvalidDesignation() {
        //arrange
        String designation = "Account123";
        String personIDString = "email@gmail.com";
        String accountType = "current";
        //act - assert
        assertThrows(IllegalArgumentException.class, () -> service.addBankAccount(designation, personIDString, accountType));
    }

    @Test
    @DisplayName("Failure add current account: invalid designation")
    void failureAddCurrentAccount_NullDesignation() {
        //arrange
        String personIDString = "email@gmail.com";
        String accountType = "current";
        //act - assert
        assertThrows(NullPointerException.class, () -> service.addBankAccount(null, personIDString, accountType));
    }

    @Test
    @DisplayName("Failure add current account: person not found")
    void failureAddCurrentAccount_PersonNotFound() throws Exception{
        //arrange
        String designation = "Account";
        String personIDString = "email@gmail.com";
        String accountType = "current";
        doThrow(ElementNotFoundException.class).when(personRepository).findByID(Mockito.any(Email.class));
        //act - assert
        assertThrows(ElementNotFoundException.class, () -> service.addBankAccount(designation, personIDString, accountType));
    }

    @Test
    @DisplayName("Failure add current account: designation already used")
    void failureAddCurrentAccount_DesignationAlreadyUsed() throws Exception {
        //arrange
        String designation = "Account";
        String personIDString = "email@gmail.com";
        String accountType = "current";
        CashAccount cashAccount = Mockito.mock(CashAccount.class);
        AccountIDList idList = Mockito.mock(AccountIDList.class);
        AccountID id = Mockito.mock(AccountID.class);
        idList.add(id);
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        when(person.getAccountIDList()).thenReturn(idList);
        when(idList.size()).thenReturn(1);
        when(idList.getID(0)).thenReturn(id);
        when(accountRepository.findByID(Mockito.any(AccountID.class))).thenReturn(cashAccount);
        when(cashAccount.isSameDesignation(new AccountDesignation(designation))).thenReturn(true);
        //act - assert
        assertThrows(AccountNotCreatedException.class, () -> service.addBankAccount(designation, personIDString, accountType));
    }

    @Test
    @DisplayName("Failure add current account: another account type (internal error)")
    void failureAddCurrentAccount_AnotherAccountType() throws Exception {
        //arrange
        String designation = "Account";
        String holderID = "email@gmail.com";
        Method method = AccountService.class.getDeclaredMethod("addBankAccount", String.class, String.class, String.class);
        method.setAccessible(true);
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        when(person.getAccountIDList()).thenReturn(new AccountIDList());
        //act - assert
        try {
            method.invoke(service, designation, holderID, "BankAccount");
            fail("should have thrown an exception");
        } catch (InvocationTargetException e) {
            Throwable exception = e.getCause();
            assertThat(exception)
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }

    @Test
    @DisplayName("Get cash account balance successfully")
    void getCashAccountBalanceSuccessfully() throws Exception {
        //arrange
        String adminId = "admin@gmail.com";
        AccountID accountID = new AccountID(UUID.randomUUID());
        CashAccount cashAccount = Mockito.mock(CashAccount.class);
        when(accountRepository.findByID(Mockito.any(AccountID.class))).thenReturn(cashAccount);
        when(cashAccount.getAccountType()).thenReturn(Constants.CASH_ACCOUNT_TYPE);
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        FamilyID familyID = Mockito.mock(FamilyID.class);
        when(person.getFamilyID()).thenReturn(familyID);
        Family family = Mockito.mock(Family.class);
        when(familyRepository.findByID(familyID)).thenReturn(family);
        when(family.isAdministrator(Mockito.any(Email.class))).thenReturn(true);
        when(family.ownsCashAccount(accountID)).thenReturn(true);

        when(cashAccount.getBalance()).thenReturn(new MoneyValue(new BigDecimal("10")));
        MoneyValue expected = new MoneyValue(new BigDecimal("10"));
        //act
        MoneyValue result = service.getCashAccountBalance(adminId, accountID.toString());
        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Check account balance successfully")
    void checkAccountBalanceSuccessfully() throws Exception{
        //arrange
        String holderID = "email@gmail.com";
        String accountID = UUID.randomUUID().toString();
        Account account = Mockito.mock(Account.class);
        MoneyValue expected = new MoneyValue(BigDecimal.valueOf(26.21));
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        when(person.isMyAccount(Mockito.any(AccountID.class))).thenReturn(true);
        when(accountRepository.findByID(Mockito.any(AccountID.class))).thenReturn(account);
        when(account.getBalance()).thenReturn(expected);
        //act
        MoneyValue result = service.checkAccountBalance(accountID, holderID);
        //assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Failure check account balance: holder not found")
    void failureCheckAccountBalance_HolderNotFound() throws Exception {
        //arrange
        String holderID = "email@gmail.com";
        String accountID = UUID.randomUUID().toString();
        doThrow(ElementNotFoundException.class).when(personRepository).findByID(Mockito.any(Email.class));
        //act - assert
        assertThrows(ElementNotFoundException.class, () -> service.checkAccountBalance(accountID, holderID));
    }

    @Test
    @DisplayName("Failure check account balance: invalid owner")
    void failureCheckAccountBalance_InvalidOwner() throws Exception {
        //arrange
        String holderID = "email@gmail.com";
        String accountID = UUID.randomUUID().toString();
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        when(person.isMyAccount(Mockito.any(AccountID.class))).thenReturn(false);
        //act - assert
        assertThrows(InvalidAccountOwner.class, () -> service.checkAccountBalance(accountID, holderID));
    }

    @Test
    @DisplayName("Failure check account balance: account not found")
    void failureCheckAccountBalance_AccountNotFound() throws Exception {
        //arrange
        String holderID = "email@gmail.com";
        String accountID = UUID.randomUUID().toString();
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        when(person.isMyAccount(Mockito.any(AccountID.class))).thenReturn(true);
        doThrow(ElementNotFoundException.class).when(accountRepository).findByID(Mockito.any(AccountID.class));
        //act - assert
        assertThrows(ElementNotFoundException.class, () -> service.checkAccountBalance(accountID, holderID));
    }


    @Test
    @DisplayName("create Personal Cash Account: Successful case")
    void createPersonalCashAccount_Success() throws Exception {
        //arrange
        String designation = "Account";
        String holderID = "email@gmail.com";
        double initialAmount = 10;
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        when(accountDGenerator.generate()).thenReturn(new AccountID(UUID.randomUUID()));
        //act - assert
        PersonalCashAccountOutDTO result = service.createPersonalCashAccount(holderID, initialAmount, designation);
        assertNotNull(result);
    }

    @Test
    @DisplayName("create Personal Cash Account: Successful case")
    void createPersonalCashAccount_CreateTwoAccounts() throws Exception {
        //arrange
        String designation = "Account";
        String holderID = "email@gmail.com";
        double initialAmount = 10;
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        when(accountDGenerator.generate()).thenReturn(new AccountID(UUID.randomUUID()));
        service.createPersonalCashAccount(holderID, initialAmount, designation);
        //act - assert
        PersonalCashAccountOutDTO result = service.createPersonalCashAccount(holderID, initialAmount, designation);
        assertNotNull(result);

    }

    @Test
    @DisplayName("create Personal Cash Account: Invalid Amount")
    void createPersonalCashAccount_InvalidAmount() throws Exception {
        //arrange
        String designation = "Account";
        String holderID = "email@gmail.com";
        double initialAmount = -10;
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);

        //act - assert
        assertThrows(AccountNotCreatedException.class, () -> service.createPersonalCashAccount(holderID, initialAmount, designation));
    }

    @Test
    @DisplayName("create Personal Cash Account: Invalid Designation")
    void createPersonalCashAccount_InvalidDesignation() throws Exception {
        //arrange
        String designation = "321%$#2";
        String holderID = "email@gmail.com";
        double initialAmount = 10;
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        //act - assert
        assertThrows(IllegalArgumentException.class, () -> service.createPersonalCashAccount(holderID, initialAmount, designation));
    }

    @Test
    @DisplayName("create Personal Cash Account: Invalid Email")
    void createPersonalCashAccount_InvalidEmail() throws Exception {
        //arrange
        String designation = "321%$#2";
        String holderID = "email@gmail.com";
        double initialAmount = 10;
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        //act - assert
        assertThrows(IllegalArgumentException.class, () -> service.createPersonalCashAccount(holderID, initialAmount, designation));
    }


    @Test
    @DisplayName("create Family Cash Account: Successful case")
    void createFamilyCashAccount_Success() throws Exception {
        //arrange
        String designation = "Account";
        String adminID = "email@gmail.com";
        double initialAmount = 10;
        String familyID = UUID.randomUUID().toString();
        when(familyRepository.findByID(Mockito.any(FamilyID.class))).thenReturn(family);
        when(family.isAdministrator(Mockito.any(Email.class))).thenReturn(true);
        when(family.hasCashAccount()).thenReturn(false);
        //act - assert
        FamilyCashAccountOutDTO result = service.createFamilyCashAccount(familyID, adminID, initialAmount, designation);
        assertNotNull(result);

    }

    @Test
    @DisplayName("create Family Cash Account - Unsuccessful case: Not a family administrator")
    void createFamilyCashAccount_NotFamilyAdministrator() throws Exception {
        //arrange
        String designation = "Account";
        String adminID = "email@gmail.com";
        double initialAmount = 10;
        String familyID = UUID.randomUUID().toString();
        when(familyRepository.findByID(Mockito.any(FamilyID.class))).thenReturn(family);
        when(family.isAdministrator(Mockito.any(Email.class))).thenReturn(false);
        //act - assert
        assertThrows(IllegalArgumentException.class, () -> service.createFamilyCashAccount(familyID, adminID, initialAmount, designation));
    }

    @Test
    @DisplayName("create Family Cash Account - Unsuccessful case: Family already has a cash account")
    void createFamilyCashAccount_FamilyAlreadyHasACashAccount() throws Exception{
        String designation = "Account";
        String adminID = "email@gmail.com";
        double initialAmount = 10;
        String familyID = UUID.randomUUID().toString();
        when(familyRepository.findByID(Mockito.any(FamilyID.class))).thenReturn(family);
        when(family.isAdministrator(Mockito.any(Email.class))).thenReturn(true);
        when(family.hasCashAccount()).thenReturn(true);
        //act - assert
        assertThrows(IllegalArgumentException.class, () -> service.createFamilyCashAccount(familyID, adminID, initialAmount, designation));
    }

    @Test
    @DisplayName("create Family Cash Account: Invalid Amount")
    void createFamilyCashAccount_InvalidAmount() throws Exception {
        //arrange
        String designation = "Account";
        String adminID = "email@gmail.com";
        double initialAmount = -10;
        String familyID = UUID.randomUUID().toString();
        when(familyRepository.findByID(Mockito.any(FamilyID.class))).thenReturn(family);
        when(family.isAdministrator(Mockito.any(Email.class))).thenReturn(true);
        when(family.hasCashAccount()).thenReturn(false);
        //act - assert
        assertThrows(AccountNotCreatedException.class, () -> service.createFamilyCashAccount(familyID, adminID, initialAmount, designation));
    }

    @Test
    @DisplayName("create Family Cash Account: Invalid Designation")
    void createFamilyCashAccount_InvalidDesignation() throws Exception{
        //arrange
        String designation = "321%$#2";
        String adminID = "email@gmail.com";
        double initialAmount = 10;
        String familyID = UUID.randomUUID().toString();
        when(familyRepository.findByID(Mockito.any(FamilyID.class))).thenReturn(family);
        when(family.isAdministrator(Mockito.any(Email.class))).thenReturn(true);
        when(family.hasCashAccount()).thenReturn(false);
        //act - assert
        assertThrows(IllegalArgumentException.class, () -> service.createFamilyCashAccount(familyID, adminID, initialAmount, designation));
    }

    @Test
    @DisplayName("create Family Cash Account: Invalid Email")
    void createFamilyCashAccount_InvalidEmail() {
        //arrange
        FamilyID familyID = Mockito.mock(FamilyID.class);
        String designation = "MyCashAccount";
        String adminID = "emailgmail.com";
        double initialAmount = 10;
        //act - assert
        assertThrows(InvalidEmailException.class, () -> service.createFamilyCashAccount(familyID.toString(), adminID, initialAmount, designation));
    }

    @Test
    @DisplayName("Add bank savings account successfully")
    void addBankSavingsAccountSuccessfully() throws Exception {
        //arrange
        String designation = "Account";
        String personIDString = "email@gmail.com";
        String accountType = "savings";
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        when(person.getAccountIDList()).thenReturn(new AccountIDList());
        doNothing().when(accountRepository).save(Mockito.any(Account.class));
        when(accountDGenerator.generate()).thenReturn(new AccountID(UUID.randomUUID()));
        doNothing().when(personRepository).save(person);
        //act - assert
        assertDoesNotThrow(() -> service.addBankAccount(designation, personIDString, accountType));
    }

    @Test
    @DisplayName("Add credit account successfully")
    void addCreditAccountSuccessfully() throws Exception {
        //arrange
        String designation = "Account";
        String personIDString = "email@gmail.com";
        String accountType = "credit";
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        when(person.getAccountIDList()).thenReturn(new AccountIDList());
        doNothing().when(accountRepository).save(Mockito.any(Account.class));
        when(accountDGenerator.generate()).thenReturn(new AccountID(UUID.randomUUID()));
        doNothing().when(personRepository).save(person);
        //act - assert
        assertDoesNotThrow(() -> service.addBankAccount(designation, personIDString, accountType));
    }

    @Test
    @DisplayName("Failure add bank savings account: null email")
    void failureAddBankSavingsAccount_NullEmail() {
        //arrange
        String designation = "Account";
        String accountType = "savings";
        //act - assert
        assertThrows(NullPointerException.class, () -> service.addBankAccount(designation, null, accountType));
    }

    @Test
    @DisplayName("Add credit account successfully")
    void addCreditAccountSuccessfullyWithDTO() throws Exception {
        //arrange
        String designation = "Account";
        String personIDString = "email@gmail.com";
        String accountType = "credit";
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        when(person.getAccountIDList()).thenReturn(new AccountIDList());
        doNothing().when(accountRepository).save(Mockito.any(Account.class));
        when(accountDGenerator.generate()).thenReturn(new AccountID(UUID.randomUUID()));
        doNothing().when(personRepository).save(person);
        //act - assert
        AccountOutDTO result = service.addBankAccount(designation, personIDString, accountType);
        assertNotNull(result);
    }

    @Test
    @DisplayName("Add bank account successfully")
    void addBankSavingsAccountSuccessfullyWithDTO() throws Exception {
        //arrange
        String designation = "Account";
        String personIDString = "email@gmail.com";
        String accountType = "savings";
        when(personRepository.findByID(Mockito.any(Email.class))).thenReturn(person);
        when(person.getAccountIDList()).thenReturn(new AccountIDList());
        doNothing().when(accountRepository).save(Mockito.any(Account.class));
        when(accountDGenerator.generate()).thenReturn(new AccountID(UUID.randomUUID()));
        doNothing().when(personRepository).save(person);
        //act - assert
        AccountOutDTO result = service.addBankAccount(designation, personIDString, accountType);
        assertNotNull(result);
    }

}
