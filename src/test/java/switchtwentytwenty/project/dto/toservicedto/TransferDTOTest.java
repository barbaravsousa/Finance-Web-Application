package switchtwentytwenty.project.dto.toservicedto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TransferDTOTest {

    @Test
    void createTransferDTO_getDescription(){
        //arrange
        TransferDTO dto = new TransferDTO.TransferDTOBuilder()
                            .amount(100)
                            .categoryId(UUID.randomUUID().toString())
                            .date("2020-12-12")
                            .description("Transfer")
                            .destinationAccountId(UUID.randomUUID().toString())
                            .originAccountId(UUID.randomUUID().toString())
                            .receiverId(UUID.randomUUID().toString())
                            .senderId(UUID.randomUUID().toString())
                            .build();
        //act
        String result = dto.getDescription();
        //assert
        assertEquals("Transfer", result);
    }

    @Test
    void createTransferDTO_getAmount(){
        //arrange
        TransferDTO dto = new TransferDTO.TransferDTOBuilder()
                .amount(100)
                .categoryId(UUID.randomUUID().toString())
                .date("2020-12-12")
                .description("Transfer")
                .destinationAccountId(UUID.randomUUID().toString())
                .originAccountId(UUID.randomUUID().toString())
                .receiverId(UUID.randomUUID().toString())
                .senderId(UUID.randomUUID().toString())
                .build();
        //act
        double result = dto.getAmount();
        //assert
        assertEquals(100, result);
    }

    @Test
    void createTransferDTO_getCategoryID(){
        //arrange
        String id = "6f16276a-b262-11eb-8529-0242ac130003";
        TransferDTO dto = new TransferDTO.TransferDTOBuilder()
                .amount(100)
                .categoryId(id)
                .date("2020-12-12")
                .description("Transfer")
                .destinationAccountId(UUID.randomUUID().toString())
                .originAccountId(UUID.randomUUID().toString())
                .receiverId(UUID.randomUUID().toString())
                .senderId(UUID.randomUUID().toString())
                .build();
        //act
        String result = dto.getCategoryID();
        //assert
        assertEquals(id, result);
    }

    @Test
    void createTransferDTO_getDesinationAccount(){
        //arrange
        String id = "6f16276a-b262-11eb-8529-0242ac130003";
        TransferDTO dto = new TransferDTO.TransferDTOBuilder()
                .amount(100)
                .categoryId(UUID.randomUUID().toString())
                .date("2020-12-12")
                .description("Transfer")
                .destinationAccountId(id)
                .originAccountId(UUID.randomUUID().toString())
                .receiverId(UUID.randomUUID().toString())
                .senderId(UUID.randomUUID().toString())
                .build();
        //act
        String result = dto.getDestinationAccountID();
        //assert
        assertEquals(id, result);
    }

    @Test
    void createTransferDTO_getOriginAccount(){
        //arrange
        String id = "6f16276a-b262-11eb-8529-0242ac130003";
        TransferDTO dto = new TransferDTO.TransferDTOBuilder()
                .amount(100)
                .categoryId(UUID.randomUUID().toString())
                .date("2020-12-12")
                .description("Transfer")
                .destinationAccountId(UUID.randomUUID().toString())
                .originAccountId(id)
                .receiverId(UUID.randomUUID().toString())
                .senderId(UUID.randomUUID().toString())
                .build();
        //act
        String result = dto.getOriginAccountID();
        //assert
        assertEquals(id, result);
    }

    @Test
    void createTransferDTO_getReceiverID(){
        //arrange
        String id = "6f16276a-b262-11eb-8529-0242ac130003";
        TransferDTO dto = new TransferDTO.TransferDTOBuilder()
                .amount(100)
                .categoryId(UUID.randomUUID().toString())
                .date("2020-12-12")
                .description("Transfer")
                .destinationAccountId(UUID.randomUUID().toString())
                .originAccountId(UUID.randomUUID().toString())
                .receiverId(id)
                .senderId(UUID.randomUUID().toString())
                .build();
        //act
        String result = dto.getReceiverID();
        //assert
        assertEquals(id, result);
    }

    @Test
    void createTransferDTO_getSenderID(){
        //arrange
        String id = "6f16276a-b262-11eb-8529-0242ac130003";
        TransferDTO dto = new TransferDTO.TransferDTOBuilder()
                .amount(100)
                .categoryId(UUID.randomUUID().toString())
                .date("2020-12-12")
                .description("Transfer")
                .destinationAccountId(UUID.randomUUID().toString())
                .originAccountId(UUID.randomUUID().toString())
                .receiverId(UUID.randomUUID().toString())
                .senderId(id)
                .build();
        //act
        String result = dto.getSenderID();
        //assert
        assertEquals(id, result);
    }

    @Test
    void createTransferDTO_getDate(){
        //arrange
        TransferDTO dto = new TransferDTO.TransferDTOBuilder()
                .amount(100)
                .categoryId(UUID.randomUUID().toString())
                .date("2020-12-12")
                .description("Transfer")
                .destinationAccountId(UUID.randomUUID().toString())
                .originAccountId(UUID.randomUUID().toString())
                .receiverId(UUID.randomUUID().toString())
                .senderId(UUID.randomUUID().toString())
                .build();
        //act
        String result = dto.getDate();
        //assert
        assertEquals("2020-12-12", result);
    }
}