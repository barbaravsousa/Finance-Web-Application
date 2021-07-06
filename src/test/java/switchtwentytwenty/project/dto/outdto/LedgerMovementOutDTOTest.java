package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LedgerMovementOutDTOTest {

    @Test
    @DisplayName("Test getters")
    void getters() {
        //arrange
        String date = "11/04/2001";
        String movementType = "CREDIT";
        double amount = 20.00;
        String familyMember = "Maria";
        String senderAccount = UUID.randomUUID().toString();
        String receiverAccount = UUID.randomUUID().toString();
        String description = "The money you lent me 5 years ago";
        String category = "Living Expenses";
        double balanceToThisDate = 800.00;
        LedgerMovementOutDTO ledgerMovementOutDTO = new LedgerMovementOutDTO();
        ledgerMovementOutDTO.setDate(date);
        ledgerMovementOutDTO.setMovementType(movementType);
        ledgerMovementOutDTO.setAmount(amount);
        ledgerMovementOutDTO.setFamilyMember(familyMember);
        ledgerMovementOutDTO.setSenderAccount(senderAccount);
        ledgerMovementOutDTO.setReceiverAccount(receiverAccount);
        ledgerMovementOutDTO.setDescription(description);
        ledgerMovementOutDTO.setCategory(category);
        ledgerMovementOutDTO.setBalanceToThisDate(balanceToThisDate);
        //Act and Assert
        assertEquals(date, ledgerMovementOutDTO.getDate());
        assertEquals(movementType, ledgerMovementOutDTO.getMovementType());
        assertEquals(amount, ledgerMovementOutDTO.getAmount());
        assertEquals(familyMember, ledgerMovementOutDTO.getFamilyMember());
        assertEquals(senderAccount, ledgerMovementOutDTO.getSenderAccount());
        assertEquals(receiverAccount, ledgerMovementOutDTO.getReceiverAccount());
        assertEquals(description, ledgerMovementOutDTO.getDescription());
        assertEquals(category, ledgerMovementOutDTO.getCategory());
        assertEquals(balanceToThisDate, ledgerMovementOutDTO.getBalanceToThisDate());

    }

    @Test
    @DisplayName("Test equals: same dto")
    void equalsSameDTO() {
        //arrange
        LedgerMovementOutDTO ledgerMovementOutDTO = new LedgerMovementOutDTO();
        //act
        boolean result = ledgerMovementOutDTO.equals(ledgerMovementOutDTO);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test equals: null")
    void equals_null() {
        //arrange
        LedgerMovementOutDTO ledgerMovementOutDTO = new LedgerMovementOutDTO();
        //act
        boolean result = ledgerMovementOutDTO.equals(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test equals: different object")
    void equals_differentObject() {
        //arrange
        LedgerMovementOutDTO ledgerMovementOutDTO = new LedgerMovementOutDTO();
        //act
        boolean result = ledgerMovementOutDTO.equals("   ");
        //assert
        assertFalse(result);
    }


    @Test
    @DisplayName("Same Hashcode")
    void sameHashCode() {
        //arrange
        LedgerMovementOutDTO ledgerMovementOutDTO = new LedgerMovementOutDTO();
        LedgerMovementOutDTO sameDTO = new LedgerMovementOutDTO();
        //act
        int hashCode1 = ledgerMovementOutDTO.hashCode();
        int hashCode2 = sameDTO.hashCode();
        //assert
        assertEquals(hashCode1,hashCode2);
    }

    @Test
    @DisplayName("Different Hashcode")
    void differentHashcode() {
        //arrange
        String date = "11/04/2001";
        String movementType = "CREDIT";
        double amount = 20.00;
        String familyMember = "Maria";
        String senderAccount = UUID.randomUUID().toString();
        String receiverAccount = UUID.randomUUID().toString();
        String description = "The money you lent me 5 years ago";
        String category = "Living Expenses";
        double balanceToThisDate = 800.00;
        LedgerMovementOutDTO ledgerMovementOutDTO = new LedgerMovementOutDTO();
        ledgerMovementOutDTO.setDate(date);
        ledgerMovementOutDTO.setMovementType(movementType);
        ledgerMovementOutDTO.setAmount(amount);
        ledgerMovementOutDTO.setFamilyMember(familyMember);
        ledgerMovementOutDTO.setSenderAccount(senderAccount);
        ledgerMovementOutDTO.setReceiverAccount(receiverAccount);
        ledgerMovementOutDTO.setDescription(description);
        ledgerMovementOutDTO.setCategory(category);
        ledgerMovementOutDTO.setBalanceToThisDate(balanceToThisDate);
        LedgerMovementOutDTO differentDTO =new LedgerMovementOutDTO();
        //act
        int hashCode1 = ledgerMovementOutDTO.hashCode();
        int hashCode2 = differentDTO.hashCode();
        //assert
        assertNotEquals(hashCode1,hashCode2);


    }

}