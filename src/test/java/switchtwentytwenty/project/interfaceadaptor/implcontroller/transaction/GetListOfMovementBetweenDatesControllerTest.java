package switchtwentytwenty.project.interfaceadaptor.implcontroller.transaction;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ILedgerService;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.dto.outdto.MovementOutDTO;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetListOfMovementBetweenDatesControllerTest {

    @Mock
    ILedgerService ledgerServiceMock;
    @Mock
    HttpServletRequest request;
    @Mock
    Principal principal;
    @InjectMocks
    GetListOfMovementsBetweenDatesController controller;


    @Test
    @DisplayName("Get a list of movements - positive response with empty list")
    void getListOfMovementsPositiveResponseWithEmptyList() throws Exception {
        //arrange

        int statusCodeExpected = 204;


        String user = "Maggy";
        String accountID = UUID.randomUUID().toString();
        String startDate = "1900-01-01";
        String endDate = "2021-01-01";

        //arrange mock
        doReturn(principal).when(request).getUserPrincipal();
        doReturn(user).when(principal).getName();
        doReturn(Collections.EMPTY_LIST).when(ledgerServiceMock).getListOfMovementsBetweenDates(anyString(), anyString(), anyString(), anyString());

        //act
        ResponseEntity<Object> response = controller.getListOfMovementsBetweenDates(request, accountID, startDate, endDate);

        //assert
        assertEquals(response.getStatusCodeValue(), statusCodeExpected);
    }

    @Test
    @DisplayName("Get a list of movements - positive response")
    void getListOfMovementsPositiveResponse() throws Exception {
        //arrange
        int statusCodeExpected = 200;

        String user = "Maggy";
        String accountID = UUID.randomUUID().toString();
        String startDate = "1900-01-01";
        String endDate = "2021-01-01";

        MovementOutDTO dto = new MovementOutDTO.OutMovementDTOBuilder()
                .withAmount(100)
                .withDate("2019-01-01")
                .withCategory("Food")
                .withAccountID(UUID.randomUUID().toString())
                .withDescription("Delicious")
                .withBalanceToThisDate(500)
                .withMovementType(Constants.CREDIT)
                .build();

        //arrange mock
        doReturn(user).when(principal).getName();
        doReturn(principal).when(request).getUserPrincipal();
        doReturn(Collections.singletonList(dto)).when(ledgerServiceMock).getListOfMovementsBetweenDates(anyString(), anyString(), anyString(), anyString());

        //act
        ResponseEntity<Object> response = controller.getListOfMovementsBetweenDates(request, accountID, startDate, endDate);

        //assert
        assertEquals(response.getStatusCodeValue(), statusCodeExpected);
    }

    @Test
    @DisplayName("Get a list of movements - exception was trown IllegalStateException")
    void getListOfMovementsIllegalStateException() throws Exception {
        //arrange
        int statusCodeExpected = 422;

        String user = "Maggy";
        String accountID = UUID.randomUUID().toString();
        String startDate = "1900-01-01";
        String endDate = "2021-01-01";

        //arrange mock
        doReturn(user).when(principal).getName();
        doReturn(principal).when(request).getUserPrincipal();
        doThrow(IllegalStateException.class).when(ledgerServiceMock).getListOfMovementsBetweenDates(anyString(), anyString(), anyString(), anyString());

        //act
        ResponseEntity<Object> response = controller.getListOfMovementsBetweenDates(request, accountID, startDate, endDate);

        //assert
        assertEquals(response.getStatusCodeValue(), statusCodeExpected);
    }
}
