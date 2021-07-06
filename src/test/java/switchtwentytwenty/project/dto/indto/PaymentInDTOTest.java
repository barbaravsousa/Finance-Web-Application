package switchtwentytwenty.project.dto.indto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PaymentInDTOTest {


    @Test
    void createInPaymentDTO_getAmount(){
        //arrange
        PaymentInDTO dto = new PaymentInDTO.PaymentDTOBuilder()
                .withAmount(50)
                .withCategoryID(UUID.randomUUID().toString())
                .withDate("2020-12-12")
                .withDesignation("Dinner with friends")
                .withPersonID("jones@gmail.com")
                .build();
        //act
        double result = dto.getAmount();
        //assert
        assertEquals(50, result);
    }

    @Test
    void createInPaymentDTO_getDate(){
        //arrange
        PaymentInDTO dto = new PaymentInDTO.PaymentDTOBuilder()
                .withAmount(50)
                .withCategoryID(UUID.randomUUID().toString())
                .withDate("2020-12-12")
                .withDesignation("Dinner with friends")
                .withPersonID("jones@gmail.com")
                .build();
        //act
        String result = dto.getDate();
        //assert
        assertEquals("2020-12-12", result);
    }

    @Test
    void createInPaymentDTO_getCategoryID(){
        //arrange
        String id = "6f16276a-b262-11eb-8529-0242ac130003";
        PaymentInDTO dto = new PaymentInDTO.PaymentDTOBuilder()
                .withAmount(50)
                .withCategoryID(id)
                .withDate("2020-12-12")
                .withDesignation("Dinner with friends")
                .withPersonID("jones@gmail.com")
                .build();
        //act
        String result = dto.getCategoryID();
        //assert
        assertEquals(id, result);
    }

    @Test
    void createInPaymentDTO_getDesignation(){
        //arrange
        String id = "6f16276a-b262-11eb-8529-0242ac130003";
        PaymentInDTO dto = new PaymentInDTO.PaymentDTOBuilder()
                .withAmount(50)
                .withCategoryID(UUID.randomUUID().toString())
                .withDate("2020-12-12")
                .withDesignation("Dinner with friends")
                .withPersonID("jones@gmail.com")
                .build();
        //act
        String result = dto.getDesignation();
        //assert
        assertEquals("Dinner with friends", result);
    }

    @Test
    void createInPaymentDTO_getPersonID(){
        //arrange
        String id = "6f16276a-b262-11eb-8529-0242ac130003";
        PaymentInDTO dto = new PaymentInDTO.PaymentDTOBuilder()
                .withAmount(50)
                .withCategoryID(UUID.randomUUID().toString())
                .withDate("2020-12-12")
                .withDesignation("Dinner with friends")
                .withPersonID("jones@gmail.com")
                .build();
        //act
        String result = dto.getPersonID();
        //assert
        assertEquals("jones@gmail.com", result);
    }
}