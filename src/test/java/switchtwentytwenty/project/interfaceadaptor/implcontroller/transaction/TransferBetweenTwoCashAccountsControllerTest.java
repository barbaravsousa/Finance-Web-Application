package switchtwentytwenty.project.interfaceadaptor.implcontroller.transaction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.TransactionService;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.ID;
import switchtwentytwenty.project.dto.indto.TransferInDTO;
import switchtwentytwenty.project.dto.toservicedto.TransferDTO;
import switchtwentytwenty.project.exception.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TransferBetweenTwoCashAccountsControllerTest {

    @InjectMocks
    TransferBetweenTwoCashAccountsController transferBetweenTwoCashAccountsController;
    @Mock
    TransactionService transactionService;

    @Test
    @DisplayName("Throw Illegal Argument Exception")
    void throwIllegalArgument() throws Exception {

        String adminID = "churchill@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        ID familyID = new FamilyID(familyUUID);

        TransferInDTO info = new TransferInDTO();
        info.setSenderId(familyID.toString());
        info.setReceiverId(adminID);
        info.setAmount(70);

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(info.getSenderId())
                .receiverId(info.getReceiverId())
                .amount(info.getAmount())
                .build();

        Mockito.doThrow(IllegalArgumentException.class).when(transactionService).transferBetweenCashAccounts(transferDTO);

        ResponseEntity<Object> result = transferBetweenTwoCashAccountsController.transferBetweenTwoCashAccounts(info);
        int expected = 400;

        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Null pointer Exception")
    void nullPointerException() throws Exception{

        String adminID = "churchill@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        ID familyID = new FamilyID(familyUUID);

        TransferInDTO info = new TransferInDTO();
        info.setSenderId(familyID.toString());
        info.setReceiverId(adminID);
        info.setAmount(70);

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(info.getSenderId())
                .receiverId(info.getReceiverId())
                .amount(info.getAmount())
                .build();

        Mockito.doThrow(NullPointerException.class).when(transactionService).transferBetweenCashAccounts(transferDTO);

        ResponseEntity<Object> result = transferBetweenTwoCashAccountsController.transferBetweenTwoCashAccounts(info);
        int expected = 400;

        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Parse Exception")
    void parseException() throws Exception{

        String adminID = "churchill@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        ID familyID = new FamilyID(familyUUID);

        TransferInDTO info = new TransferInDTO();
        info.setSenderId(familyID.toString());
        info.setReceiverId(adminID);
        info.setAmount(70);

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(info.getSenderId())
                .receiverId(info.getReceiverId())
                .amount(info.getAmount())
                .build();

        Mockito.doThrow(ParseException.class).when(transactionService).transferBetweenCashAccounts(transferDTO);

        ResponseEntity<Object> result = transferBetweenTwoCashAccountsController.transferBetweenTwoCashAccounts(info);
        int expected = 400;

        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Invalid Email Exception")
    void invalidEmailException() throws Exception{

        String adminID = "churchill@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        ID familyID = new FamilyID(familyUUID);

        TransferInDTO info = new TransferInDTO();
        info.setSenderId(familyID.toString());
        info.setReceiverId(adminID);
        info.setAmount(70);

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(info.getSenderId())
                .receiverId(info.getReceiverId())
                .amount(info.getAmount())
                .build();

        Mockito.doThrow(InvalidEmailException.class).when(transactionService).transferBetweenCashAccounts(transferDTO);

        ResponseEntity<Object> result = transferBetweenTwoCashAccountsController.transferBetweenTwoCashAccounts(info);
        int expected = 400;

        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Element Not Found Exception")
    void elementNotFoundException() throws Exception {

        String adminID = "churchill@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        ID familyID = new FamilyID(familyUUID);

        TransferInDTO info = new TransferInDTO();
        info.setSenderId(familyID.toString());
        info.setReceiverId(adminID);
        info.setAmount(70);

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(info.getSenderId())
                .receiverId(info.getReceiverId())
                .amount(info.getAmount())
                .build();

        Mockito.doThrow(ElementNotFoundException.class).when(transactionService).transferBetweenCashAccounts(transferDTO);

        ResponseEntity<Object> result = transferBetweenTwoCashAccountsController.transferBetweenTwoCashAccounts(info);
        int expected = 400;

        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Invalid Movement Type Exception")
    void invalidMovementTypeException() throws Exception{
        String adminID = "churchill@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        ID familyID = new FamilyID(familyUUID);

        TransferInDTO info = new TransferInDTO();
        info.setSenderId(familyID.toString());
        info.setReceiverId(adminID);
        info.setAmount(70);

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(info.getSenderId())
                .receiverId(info.getReceiverId())
                .amount(info.getAmount())
                .build();

        Mockito.doThrow(InvalidMovementTypeException.class).when(transactionService).transferBetweenCashAccounts(transferDTO);

        ResponseEntity<Object> result = transferBetweenTwoCashAccountsController.transferBetweenTwoCashAccounts(info);
        int expected = 400;

        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Withdraw Not Possible Exception")
    void withdrawNotPossibleException() throws Exception {

        String adminID = "churchill@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        ID familyID = new FamilyID(familyUUID);

        TransferInDTO info = new TransferInDTO();
        info.setSenderId(familyID.toString());
        info.setReceiverId(adminID);
        info.setAmount(70);

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(info.getSenderId())
                .receiverId(info.getReceiverId())
                .amount(info.getAmount())
                .build();

        Mockito.doThrow(WithdrawNotPossibleException.class).when(transactionService).transferBetweenCashAccounts(transferDTO);

        ResponseEntity<Object> result = transferBetweenTwoCashAccountsController.transferBetweenTwoCashAccounts(info);
        int expected = 400;

        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Deposit Not Possible Exception")
    void depositNotPossibleException() throws Exception  {

        String adminID = "churchill@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        ID familyID = new FamilyID(familyUUID);

        TransferInDTO info = new TransferInDTO();
        info.setSenderId(familyID.toString());
        info.setReceiverId(adminID);
        info.setAmount(70);

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(info.getSenderId())
                .receiverId(info.getReceiverId())
                .amount(info.getAmount())
                .build();

        Mockito.doThrow(DepositNotPossibleException.class).when(transactionService).transferBetweenCashAccounts(transferDTO);

        ResponseEntity<Object> result = transferBetweenTwoCashAccountsController.transferBetweenTwoCashAccounts(info);
        int expected = 400;

        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Instantiation Exception")
    void instantiationException() throws Exception {

        String adminID = "churchill@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        ID familyID = new FamilyID(familyUUID);

        TransferInDTO info = new TransferInDTO();
        info.setSenderId(familyID.toString());
        info.setReceiverId(adminID);
        info.setAmount(70);

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(info.getSenderId())
                .receiverId(info.getReceiverId())
                .amount(info.getAmount())
                .build();

        Mockito.doThrow(InstantiationException.class).when(transactionService).transferBetweenCashAccounts(transferDTO);

        ResponseEntity<Object> result = transferBetweenTwoCashAccountsController.transferBetweenTwoCashAccounts(info);
        int expected = 400;

        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Account Not Created Exception")
    void accountNotCreatedException() throws Exception {

        String adminID = "churchill@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        ID familyID = new FamilyID(familyUUID);

        TransferInDTO info = new TransferInDTO();
        info.setSenderId(familyID.toString());
        info.setReceiverId(adminID);
        info.setAmount(70);

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(info.getSenderId())
                .receiverId(info.getReceiverId())
                .amount(info.getAmount())
                .build();

        Mockito.doThrow(AccountNotCreatedException.class).when(transactionService).transferBetweenCashAccounts(transferDTO);

        ResponseEntity<Object> result = transferBetweenTwoCashAccountsController.transferBetweenTwoCashAccounts(info);
        int expected = 400;

        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Invalid Relation Type Exception")
    void invalidRelationTypeException() throws Exception {

        String adminID = "churchill@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        ID familyID = new FamilyID(familyUUID);

        TransferInDTO info = new TransferInDTO();
        info.setSenderId(familyID.toString());
        info.setReceiverId(adminID);
        info.setAmount(70);

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(info.getSenderId())
                .receiverId(info.getReceiverId())
                .amount(info.getAmount())
                .build();

        Mockito.doThrow(InvalidRelationTypeException.class).when(transactionService).transferBetweenCashAccounts(transferDTO);

        ResponseEntity<Object> result = transferBetweenTwoCashAccountsController.transferBetweenTwoCashAccounts(info);
        int expected = 400;

        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("IO Exception")
    void iOException() throws Exception{

        String adminID = "churchill@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        ID familyID = new FamilyID(familyUUID);

        TransferInDTO info = new TransferInDTO();
        info.setSenderId(familyID.toString());
        info.setReceiverId(adminID);
        info.setAmount(70);

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(info.getSenderId())
                .receiverId(info.getReceiverId())
                .amount(info.getAmount())
                .build();

        Mockito.doThrow(IOException.class).when(transactionService).transferBetweenCashAccounts(transferDTO);

        ResponseEntity<Object> result = transferBetweenTwoCashAccountsController.transferBetweenTwoCashAccounts(info);
        int expected = 400;

        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Invalid Date Exception")
    void invalidDateException() throws Exception {

        String adminID = "churchill@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        ID familyID = new FamilyID(familyUUID);

        TransferInDTO info = new TransferInDTO();
        info.setSenderId(familyID.toString());
        info.setReceiverId(adminID);
        info.setAmount(70);

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(info.getSenderId())
                .receiverId(info.getReceiverId())
                .amount(info.getAmount())
                .build();

        Mockito.doThrow(InvalidDateException.class).when(transactionService).transferBetweenCashAccounts(transferDTO);

        ResponseEntity<Object> result = transferBetweenTwoCashAccountsController.transferBetweenTwoCashAccounts(info);
        int expected = 400;

        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Invalid VAT Exception")
    void invalidVATException() throws Exception {

        String adminID = "churchill@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        ID familyID = new FamilyID(familyUUID);

        TransferInDTO info = new TransferInDTO();
        info.setSenderId(familyID.toString());
        info.setReceiverId(adminID);
        info.setAmount(70);

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(info.getSenderId())
                .receiverId(info.getReceiverId())
                .amount(info.getAmount())
                .build();

        Mockito.doThrow(InvalidVATException.class).when(transactionService).transferBetweenCashAccounts(transferDTO);

        ResponseEntity<Object> result = transferBetweenTwoCashAccountsController.transferBetweenTwoCashAccounts(info);
        int expected = 400;

        assertEquals(expected, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Invalid Person Name Exception")
    void invalidPersonNameException() throws Exception {

        String adminID = "churchill@gmail.com";
        UUID familyUUID = UUID.randomUUID();
        ID familyID = new FamilyID(familyUUID);

        TransferInDTO info = new TransferInDTO();
        info.setSenderId(familyID.toString());
        info.setReceiverId(adminID);
        info.setAmount(70);

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(info.getSenderId())
                .receiverId(info.getReceiverId())
                .amount(info.getAmount())
                .build();

        Mockito.doThrow(InvalidPersonNameException.class).when(transactionService).transferBetweenCashAccounts(transferDTO);

        ResponseEntity<Object> result = transferBetweenTwoCashAccountsController.transferBetweenTwoCashAccounts(info);
        int expected = 400;

        assertEquals(expected, result.getStatusCodeValue());
    }
}