package switchtwentytwenty.project.dto.indto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FamilyCashAccountInDTOTest {

    @Test
    void createDTOSucessfully(){
        //arrange
        String familyID = UUID.randomUUID().toString();
        double amount = 200;
        String  designation = "my cahs account";
        FamilyCashAccountInDTO dto = new FamilyCashAccountInDTO(familyID,amount, designation);
        //act
        String result2 = dto.getDesignation();
        String result3 = dto.getFamilyAdministratorID();
        double result4 = dto.getInitialAmount();
        //assert
        assertEquals(designation,result2);
        assertEquals(familyID,result3);
        assertEquals(amount,result4);
    }
}