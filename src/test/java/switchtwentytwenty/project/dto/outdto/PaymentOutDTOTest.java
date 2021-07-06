package switchtwentytwenty.project.dto.outdto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PaymentOutDTOTest {

    @Test
    void createOutPaymentDTO_getDesignation(){
        //arrange
        String designation = "Dinner with friends";
        String accountID = UUID.randomUUID().toString();
        String categoryID = UUID.randomUUID().toString();
        double amount = 100;
        String date = "2020-12-12";
        PaymentOutDTO dto = new PaymentOutDTO(designation,accountID,categoryID, amount,date);
        //act
        String result = dto.getDesignation();
        //assert
        assertEquals(designation,result);
    }

    @Test
    void createOutPaymentDTO_getAccountID(){
        //arrange
        String designation = "Dinner with friends";
        String accountID = "6f16276a-b262-11eb-8529-0242ac130003";
        String categoryID = UUID.randomUUID().toString();
        double amount = 100;
        String date = "2020-12-12";
        PaymentOutDTO dto = new PaymentOutDTO(designation,accountID,categoryID, amount,date);
        //act
        String result = dto.getAccountID();
        //assert
        assertEquals(accountID,result);
    }

    @Test
    void createOutPaymentDTO_getCategoryID(){
        //arrange
        String designation = "Dinner with friends";
        String accountID = UUID.randomUUID().toString();
        String categoryID = "6f16276a-b262-11eb-8529-0242ac130003";
        double amount = 100;
        String date = "2020-12-12";
        PaymentOutDTO dto = new PaymentOutDTO(designation,accountID,categoryID, amount,date);
        //act
        String result = dto.getCategoryID();
        //assert
        assertEquals(categoryID,result);
    }

    @Test
    void createOutPaymentDTO_getAmount(){
        //arrange
        String designation = "Dinner with friends";
        String accountID = UUID.randomUUID().toString();
        String categoryID = "6f16276a-b262-11eb-8529-0242ac130003";
        double amount = 100;
        String date = "2020-12-12";
        PaymentOutDTO dto = new PaymentOutDTO(designation,accountID,categoryID, amount,date);
        //act
        double result = dto.getAmount();
        //assert
        assertEquals(amount,result);
    }

    @Test
    void createOutPaymentDTO_getDate(){
        //arrange
        String designation = "Dinner with friends";
        String accountID = UUID.randomUUID().toString();
        String categoryID = "6f16276a-b262-11eb-8529-0242ac130003";
        double amount = 100;
        String date = "2020-12-12";
        PaymentOutDTO dto = new PaymentOutDTO(designation,accountID,categoryID, amount,date);
        //act
        String result = dto.getDate();
        //assert
        assertEquals(date,result);
    }
}