package switchtwentytwenty.project.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserEmailNotFoundExceptionTest {

    @Test
    @DisplayName("User email not found Exception")
    void userEmailNotFoundException(){

        //arrange
        String message = "User email not in the system";

        //act
        UserEmailNotFoundException exception = new UserEmailNotFoundException(message);
        String result = exception.getMessage();

        //assert
        Assertions.assertEquals(message, result);
    }

    @Test
    @DisplayName("User email not found Exception - Null message")
    void userEmailNotFoundExceptionTestNull(){

        //arrange
        String message = null;

        //act
        UserEmailNotFoundException exception = new UserEmailNotFoundException(message);
        String result = exception.getMessage();

        //assert
        Assertions.assertNull(result);
    }

    @Test
    @DisplayName("User email not found Exception - Empty message")
    void userEmailNotFoundExceptionEmpty(){

        //arrange
        String message = "";

        //act
        UserEmailNotFoundException exception = new UserEmailNotFoundException(message);
        String result = exception.getMessage();

        //assert
        Assertions.assertEquals(message, result);
    }

}
