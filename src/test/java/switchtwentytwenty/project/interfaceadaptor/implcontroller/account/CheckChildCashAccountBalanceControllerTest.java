package switchtwentytwenty.project.interfaceadaptor.implcontroller.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.AccountService;
import switchtwentytwenty.project.domain.aggregate.account.Account;
import switchtwentytwenty.project.domain.aggregate.account.AccountFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.designation.AccountDesignation;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.exception.ElementNotFoundException;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CheckChildCashAccountBalanceControllerTest {

    @InjectMocks
    CheckChildCashAccountBalanceController controller;
    @Mock
    AccountService accountService;

    @Test
    @DisplayName("Check Child Cash Account Balance - Successful case")
    void checkChildCashAccountBalance() throws Exception {

        //arrange
        String parentID = "parent@gmail.com";
        String childID = "child@gmail.com";

        MoneyValue amountValue = new MoneyValue(new BigDecimal(10));
        AccountDesignation accountDesignation = new AccountDesignation("cash");
        AccountID accountID2 = new AccountID(UUID.randomUUID());

        Account cashAccount =  AccountFactory.createCashAccount(accountID2, accountDesignation,amountValue);
        String id2 = cashAccount.getID().toString();
        //act
        MoneyValue expected = new MoneyValue(new BigDecimal(10));

        when(accountService.checkChildCashAccountBalance(parentID, childID, id2)).thenReturn(expected);
        ResponseEntity<Object> result;
        result = controller.checkChildCashAccountBalance(parentID, childID, id2);

        //assert
        assertEquals(200, result.getStatusCodeValue());

    }

    @Test
    @DisplayName("Check Child Cash Account Balance - Unsuccessful case: child id not found  ")
    void checkChildCashAccountBalance_ElementNotFound_ChildDoesNotExist() throws Exception {

        //arrange
        String parentID = "parent@gmail.com";

        MoneyValue amountValue = new MoneyValue(new BigDecimal(10));
        AccountDesignation accountDesignation = new AccountDesignation("cash");
        AccountID accountID2 = new AccountID(UUID.randomUUID());

        Account cashAccount = AccountFactory.createCashAccount(accountID2, accountDesignation,amountValue);
        String id2 = cashAccount.getID().toString();
        //act

        doThrow(ElementNotFoundException.class).when(accountService).checkChildCashAccountBalance(parentID, "", id2);
        ResponseEntity<Object> result = controller.checkChildCashAccountBalance(parentID, "", id2);

        //assert
        assertEquals(400, result.getStatusCodeValue());

    }

    @Test
    @DisplayName("Check Child Cash Account Balance - Unsuccessful case: parent id not found  ")
    void checkChildCashAccountBalance_ElementNotFound_ParentDoesNotExist() throws Exception {

        //arrange
        String childID = "child@gmail.com";

        MoneyValue amountValue = new MoneyValue(new BigDecimal(10));
        AccountDesignation accountDesignation = new AccountDesignation("cash");
        AccountID accountID2 = new AccountID(UUID.randomUUID());

        Account cashAccount = AccountFactory.createCashAccount(accountID2, accountDesignation,amountValue);
        String id2 = cashAccount.getID().toString();
        //act

        doThrow(ElementNotFoundException.class).when(accountService).checkChildCashAccountBalance("", childID, id2);
        ResponseEntity<Object> result = controller.checkChildCashAccountBalance("", childID, id2);

        //assert
        assertEquals(400, result.getStatusCodeValue());

    }

    @Test
    @DisplayName("Check Child Cash Account Balance - Unsuccessful case: account does not exist.")
    void checkChildCashAccountBalance_ElementNotFound_AccountDoesNotExist() throws Exception {

        //arrange
        String parentID = "parent@gmail.com";
        String childID = "child@gmail.com";

        //act

        doThrow(ElementNotFoundException.class).when(accountService).checkChildCashAccountBalance(parentID, childID, "");
        ResponseEntity<Object> result = controller.checkChildCashAccountBalance(parentID, childID, "");

        //assert
        assertEquals(400, result.getStatusCodeValue());

    }

    @Test
    @DisplayName("Check Child Cash Account Balance - Unsuccessful case: account is not a cash account.")
    void checkChildCashAccountBalance_ElementNotFound_NotCashAccount() throws Exception {

        //arrange
        String parentID = "parent@gmail.com";
        String childID = "child@gmail.com";
        AccountID bankSavingsAccountID = new AccountID(UUID.randomUUID());
        AccountDesignation accountDesignation = new AccountDesignation("cash");
        //act
        Account bankSavingsAccount = AccountFactory.createBankAccount(bankSavingsAccountID, accountDesignation, Constants.BANK_SAVINGS_ACCOUNT_TYPE);
        String notCashAccountID = bankSavingsAccount.getID().toString();

        doThrow(IllegalArgumentException.class).when(accountService).checkChildCashAccountBalance(parentID, childID, notCashAccountID);
        ResponseEntity<Object> result = controller.checkChildCashAccountBalance(parentID, childID, notCashAccountID);

        //assert
        assertEquals(400, result.getStatusCodeValue());

    }
}