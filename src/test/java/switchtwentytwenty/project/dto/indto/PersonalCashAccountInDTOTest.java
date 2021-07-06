package switchtwentytwenty.project.dto.indto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalCashAccountInDTOTest {

    @Test
    void getInitialAmount() {
        //arrange
        String personID = "ola@hotmail.com";
        double initialAmount = 10;
        String designation = "crypto";
        PersonalCashAccountInDTO dto = new PersonalCashAccountInDTO( initialAmount, designation);
        //act
        double result = dto.getInitialAmount();
        //assert
        assertEquals(initialAmount, result);
    }

    @Test
    void getDesignation() {
        //arrange
        String personID = "ola@hotmail.com";
        double initialAmount = 10;
        String designation = "crypto";
        PersonalCashAccountInDTO dto = new PersonalCashAccountInDTO(initialAmount, designation);
        //act
        String result = dto.getDesignation();
        //assert
        assertEquals(designation, result);
    }

}