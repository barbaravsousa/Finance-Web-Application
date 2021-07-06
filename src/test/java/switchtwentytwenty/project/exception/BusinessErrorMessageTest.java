package switchtwentytwenty.project.exception;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.exception.BusinessErrorMessage;

import static org.junit.jupiter.api.Assertions.*;

class BusinessErrorMessageTest {

    @Test
    void getErrorMessage(){
        //arrange
        String message = "error";
        int code = 400;
        BusinessErrorMessage errorMessage = new BusinessErrorMessage(message,code);
        //act
        String result = errorMessage.getErrorMessage();
        //assert
        assertEquals(message,result);
    }

    @Test
    void getErrorCode(){
        //arrange
        String message = "error";
        int code = 400;
        BusinessErrorMessage errorMessage = new BusinessErrorMessage(message,code);
        //act
        int result = errorMessage.getErrorCode();
        //assert
        assertEquals(code,result);
    }

}