package switchtwentytwenty.project.interfaceadaptor.implcontroller.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAccountService;
import switchtwentytwenty.project.domain.share.MoneyValue;
import switchtwentytwenty.project.domain.share.id.AccountID;
import switchtwentytwenty.project.exception.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetCashAccountBalanceControllerTest {

    @InjectMocks
    GetCashAccountBalanceController controller;
    @Mock
    IAccountService service;

    @Test
    @DisplayName("Get Personal cash account balance")
    void getCashAccountBalance() throws Exception {
        //arrange
        String personId = "bones@gmail.com";
        AccountID accountID = new AccountID(UUID.randomUUID());
        String accountId = accountID.toString();
        MoneyValue value = new MoneyValue(new BigDecimal(10));
        doReturn(value).when(service).getCashAccountBalance(anyString(),anyString());

        //act
        ResponseEntity<Object> result = controller.getCashAccountBalance(personId, accountId);

        //assert
        assertEquals(200, result.getStatusCodeValue());

    }


    @Test
    @DisplayName("Get Family Cash Account Balance - Successful case  ")
    void getFamilyCashAccountBalance() throws Exception {

        //arrange
            String personId = "bones@gmail.com";
            AccountID accountID = new AccountID(UUID.randomUUID());
            String accountId = accountID.toString();
            MoneyValue value = new MoneyValue(new BigDecimal(10));
            doReturn(value).when(service).getCashAccountBalance(anyString(),anyString());

            //act
            ResponseEntity<Object> result = controller.getCashAccountBalance(personId, accountId);

            //assert
            assertEquals(200, result.getStatusCodeValue());
        }


    @Test
    @DisplayName("Get cash account balance from an invalid person - Unsuccessful case  ")
    void getCashAccountBalanceFromAnInvalidPersonID() throws Exception {

        String personId = "";
        AccountID accountID = new AccountID(UUID.randomUUID());

        doThrow(InvalidEmailException.class).when(service).getCashAccountBalance(personId, accountID.toString());

        ResponseEntity<Object> result = controller.getCashAccountBalance(personId, accountID.toString());

        //assert
        assertEquals(400, result.getStatusCodeValue());
    }


    @Test
    @DisplayName("Get Family Cash Account Balance - Successful case  ")
    void getBalanceFromAnInvalidAccount() throws Exception {

        String personId = "";
        AccountID accountID = new AccountID(UUID.randomUUID());
        Optional<MoneyValue> expected = Optional.empty();
        doThrow(InvalidEmailException.class).when(service).getCashAccountBalance(personId, null);

        ResponseEntity<Object> result = controller.getCashAccountBalance(personId, null);

        //assert
        assertEquals(400, result.getStatusCodeValue());
    }
}