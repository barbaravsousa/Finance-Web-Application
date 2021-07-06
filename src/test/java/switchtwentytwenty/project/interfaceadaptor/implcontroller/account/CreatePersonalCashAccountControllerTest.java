package switchtwentytwenty.project.interfaceadaptor.implcontroller.account;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.AccountService;
import switchtwentytwenty.project.dto.indto.PersonalCashAccountInDTO;
import switchtwentytwenty.project.dto.outdto.PersonalCashAccountOutDTO;
import switchtwentytwenty.project.exception.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePersonalCashAccountControllerTest {

    @InjectMocks
    CreatePersonalCashAccountController controller;
    @Mock
    AccountService accountService;

    @Test
    void createPersonalCashAccount() throws Exception{

        //arrange
        String personID = "constantino4ever@gmail.com";
        double cashAmount = 10;
        String designation = "cash";

        PersonalCashAccountInDTO info =   new PersonalCashAccountInDTO(cashAmount, designation);
        PersonalCashAccountOutDTO outdto = Mockito.mock(PersonalCashAccountOutDTO.class);
        //act
        when(accountService.createPersonalCashAccount(personID,cashAmount,designation)).thenReturn(outdto);
        ResponseEntity<Object> result = controller.createPersonalCashAccount(personID,info);

        //assert
        assertEquals(201,result.getStatusCodeValue());

    }

    @Test
    void createPersonalCashAccount_InvalidAmount() throws Exception {

        //arrange
        String personID = "constantinos4ever@gmail.com";
        double cashAmount = -10;
        String designation = "Food";

        PersonalCashAccountInDTO info =   new PersonalCashAccountInDTO(cashAmount,designation);
        //act
        doThrow(AccountNotCreatedException.class).when(accountService).createPersonalCashAccount(personID,cashAmount,designation);
        ResponseEntity<Object> result = controller.createPersonalCashAccount(personID, info);

        //assert
        assertEquals(400,result.getStatusCodeValue());

    }

    @Test
    void createPersonalCashAccount_InvalidEmail() throws Exception {

        //arrange
        String personID = "1234@gmail.com";
        double cashAmount = 10;
        String designation = "Food";

        PersonalCashAccountInDTO info =   new PersonalCashAccountInDTO(cashAmount,designation);
        //act
        doThrow(InvalidEmailException.class).when(accountService).createPersonalCashAccount(personID,cashAmount,designation);
        ResponseEntity<Object> result = controller.createPersonalCashAccount(personID, info);

        //assert
        assertEquals(400,result.getStatusCodeValue());

    }

    @Test
    void createPersonalCashAccount_ElementNotFound() throws Exception {

        //arrange
        String personID = "constantinos4ever@gmail.com";
        double cashAmount = 10;
        String designation = "8€#";

        PersonalCashAccountInDTO info =   new PersonalCashAccountInDTO(cashAmount,designation);
        //act
        doThrow(AccountNotCreatedException.class).when(accountService).createPersonalCashAccount(personID,cashAmount,designation);
        ResponseEntity<Object> result = controller.createPersonalCashAccount(personID, info);

        //assert
        assertEquals(400,result.getStatusCodeValue());
    }
}