package switchtwentytwenty.project.exception;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class CustomRestExceptionHandlerTest {

    @InjectMocks
    CustomRestExceptionHandler handler;

    @Mock
    ElementNotFoundException elementNotFoundExceptionMock;

    @Mock
    InvalidDateException invalidDateExceptionMock;

    @Test
    @DisplayName("ElementNotFoundException handling")
    void elementNotFoundExceptionHandling(){

        //arrange
        int expectedStatus = 404;
        handler = new CustomRestExceptionHandler();

        //arrange mock
        Mockito.doReturn("Element no found").when(elementNotFoundExceptionMock).getMessage();

        //act
        ResponseEntity<String> responseEntity = handler.elementNotFoundException(elementNotFoundExceptionMock);
        int resultStatus = responseEntity.getStatusCodeValue();

        //assert
        assertEquals(expectedStatus, resultStatus);
    }

    @Test
    @DisplayName("ElementNotFoundException handling")
    void InvalidDateExceptionHandling(){

        //arrange
        int expectedStatus = 422;
        handler = new CustomRestExceptionHandler();

        //arrange mock
        Mockito.doReturn("Invalid Date").when(invalidDateExceptionMock).getMessage();

        //act
        ResponseEntity<String> responseEntity = handler.invalidDateException(invalidDateExceptionMock);
        int resultStatus = responseEntity.getStatusCodeValue();

        //assert
        assertEquals(expectedStatus, resultStatus);
    }
}
