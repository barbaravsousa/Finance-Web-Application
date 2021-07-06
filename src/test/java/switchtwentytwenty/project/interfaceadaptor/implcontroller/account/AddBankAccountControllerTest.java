package switchtwentytwenty.project.interfaceadaptor.implcontroller.account;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.AccountService;
import switchtwentytwenty.project.dto.indto.CreateAccountInDTO;
import switchtwentytwenty.project.dto.outdto.AccountOutDTO;
import switchtwentytwenty.project.exception.AccountNotCreatedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddBankAccountControllerTest {

    @InjectMocks
    AddBankAccountController controller;
    @Mock
    AccountService accountService;

    @Test
    void AddBankSavingsAccount() throws Exception {
        String designation = "Bank Savings Account";
        String email = "maria@hotmail.com";
        String accountType = "savings";
        CreateAccountInDTO info = new CreateAccountInDTO(designation, email, accountType);


        when(accountService.addBankAccount(designation, email,accountType)).thenReturn(Mockito.mock(AccountOutDTO.class));
        ResponseEntity<Object> result = controller.addBankAccount(info);
        assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Failure to add a Bank Savings Account: empty designation")
    void AddEmptyDesignation() throws Exception {
        String designation = "";
        String email = "maria@hotmail.com";
        String accountType = "savings";
        CreateAccountInDTO info = new CreateAccountInDTO(designation, email, accountType);
        doThrow(AccountNotCreatedException.class).when(accountService).addBankAccount(designation, email,accountType);
        ResponseEntity<Object> result = controller.addBankAccount(info);
        assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    void AddCreditCardAccount() throws Exception {
        String designation = "Credit Card Account";
        String email = "maria@hotmail.com";
        String accountType = "credit";
        CreateAccountInDTO info = new CreateAccountInDTO(designation, email, accountType);

        when(accountService.addBankAccount(designation, email,accountType)).thenReturn(Mockito.mock(AccountOutDTO.class));
        ResponseEntity<Object> result = controller.addBankAccount(info);
        assertEquals(201,result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Failure to add a Credit Card Account: empty designation")
    void AddAnCreditAccountWithEmptyDesignation() throws Exception {
        String designation = "";
        String email = "maria@hotmail.com";
        String accountType = "credit";
        CreateAccountInDTO info = new CreateAccountInDTO(designation, email, accountType);
        doThrow(AccountNotCreatedException.class).when(accountService).addBankAccount(designation,email,accountType);
        ResponseEntity<Object> result = controller.addBankAccount(info);
        assertEquals(400,result.getStatusCodeValue());
    }


}