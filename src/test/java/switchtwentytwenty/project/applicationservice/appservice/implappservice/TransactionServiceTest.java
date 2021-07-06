package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.domain.share.id.*;
import switchtwentytwenty.project.dto.toservicedto.TransferDTO;
import switchtwentytwenty.project.exception.*;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    TransactionService transactionService;

    @Test
    @DisplayName("Invalid Email Exception")
    void throwIllegalArgument() {

        String adminID = "churchill";
        UUID familyUUID = UUID.randomUUID();
        ID familyID = new FamilyID(familyUUID);

        TransferDTO transferDTO = new TransferDTO.TransferDTOBuilder()
                .senderId(familyID.toString())
                .receiverId(adminID)
                .amount(70)
                .build();

        assertThrows(InvalidEmailException.class, ()->  transactionService.transferBetweenCashAccounts(transferDTO)) ;
    }
}








