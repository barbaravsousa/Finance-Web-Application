package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IAuthorizationService;
import switchtwentytwenty.project.exception.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class LedgerServiceTest {

    @InjectMocks
    LedgerService ledgerServiceMock;
    @Mock
    IAuthorizationService authorizationService;


    @Test
    @DisplayName("Get list of movements between dates: invalid account ID")
    void getListMovementsInvalidAccountID() throws InvalidEmailException, ElementNotFoundException, InvalidDateException, InvalidVATException, InvalidPersonNameException, UserEmailNotFoundException {
        //arrange
        String personID = "margaret_hamilton@gmail.com";
        String accountID = UUID.randomUUID().toString();
        String startDate = "2020-12-12";
        String endDate = "2021-01-30";
        //arrange Mock
        Mockito.when(authorizationService.accessAccountAuthorization(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
        //act and assert
        assertThrows(IllegalArgumentException.class, () -> ledgerServiceMock.getListOfMovementsBetweenDates(personID, accountID, startDate, endDate));
    }
}
