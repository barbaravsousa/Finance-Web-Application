package switchtwentytwenty.project.interfaceadaptor.implcontroller.account;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.AccountService;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.dto.indto.FamilyCashAccountInDTO;
import switchtwentytwenty.project.dto.outdto.FamilyCashAccountOutDTO;
import switchtwentytwenty.project.exception.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CreateFamilyCashAccountControllerTest {

    @InjectMocks
    CreateFamilyCashAccountController controller;
    @Mock
    AccountService accountService;

    @Test
    void createFamilyCashAccount() throws Exception {

        //arrange

        String familyAdministratorID = "constantinosever@gmail.com";
        double cashAmount = 10;
        String designation = "Food";
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        FamilyCashAccountInDTO info =   new FamilyCashAccountInDTO(familyAdministratorID,cashAmount,designation);
        FamilyCashAccountOutDTO outdto = Mockito.mock(FamilyCashAccountOutDTO.class);
        //act
        when(accountService.createFamilyCashAccount(familyID.toString(),familyAdministratorID,cashAmount,designation)).thenReturn(outdto);
        ResponseEntity<Object> result = controller.createFamilyCashAccount(familyID.toString(), info);

        //assert
        assertEquals(201,result.getStatusCodeValue());

    }

    @Test
    void createFamilyCashAccount_InvalidAmount() throws Exception {

        //arrange
        String familyAdministratorID = "constantinos4ever@gmail.com";
        double cashAmount = -10;
        String designation = "Food";
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        FamilyCashAccountInDTO info =   new FamilyCashAccountInDTO(familyAdministratorID,cashAmount,designation);
        //act
        doThrow(AccountNotCreatedException.class).when(accountService).createFamilyCashAccount(familyID.toString(),familyAdministratorID,cashAmount,designation);
        ResponseEntity<Object> result = controller.createFamilyCashAccount(familyID.toString(), info);

        //assert
        assertEquals(400,result.getStatusCodeValue());

    }

    @Test
    void createFamilyCashAccount_InvalidEmail() throws Exception {

        //arrange
        String familyAdministratorID = "1234";
        double cashAmount = 10;
        String designation = "Food";
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        FamilyCashAccountInDTO info =   new FamilyCashAccountInDTO(familyAdministratorID,cashAmount,designation);
        //act
        doThrow(InvalidEmailException.class).when(accountService).createFamilyCashAccount(familyID.toString(),familyAdministratorID,cashAmount,designation);
        ResponseEntity<Object> result = controller.createFamilyCashAccount(familyID.toString(), info);

        //assert
        assertEquals(400,result.getStatusCodeValue());

    }

    @Test
    void createFamilyCashAccount_ElementNotFound() throws Exception{

        //arrange
        String familyAdministratorID = "constantinos4ever@gmail.com";
        double cashAmount = 10;
        String designation = "8€#";
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        FamilyCashAccountInDTO info =   new FamilyCashAccountInDTO(familyAdministratorID,cashAmount,designation);
        //act
        doThrow(AccountNotCreatedException.class).when(accountService).createFamilyCashAccount(familyID.toString(),familyAdministratorID,cashAmount,designation);
        ResponseEntity<Object> result = controller.createFamilyCashAccount(familyID.toString(), info);

        //assert
        assertEquals(400,result.getStatusCodeValue());
    }
}
