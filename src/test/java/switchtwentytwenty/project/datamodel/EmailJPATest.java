package switchtwentytwenty.project.datamodel;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EmailJPATest {

    @Test
    @DisplayName("Test getters")
    void testGetters() {
        //arrange
        PersonJPA personJPA = new PersonJPA();
        String email = "alan_turing@hotmail.com";
        EmailJPA emailJPA = new EmailJPA(email, personJPA);

        //act
        String emailResult = emailJPA.getEmail();

        //assert
        assertEquals(email, emailResult);
    }

    @Test
    void createEmailJPAWithNoArguments(){
        //arrange
        //act
        EmailJPA emailJPA = new EmailJPA();
        //assert
        assertNotNull(emailJPA);
    }
}
