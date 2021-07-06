package switchtwentytwenty.project.datamodel;

import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AccountIDJPATest {

    @Test
    void getPersonJPA() {
        //arrange
        String id = UUID.randomUUID().toString();
        PersonJPA personJPA = new PersonJPA();
        AccountIDJPA accountIDJPA = new AccountIDJPA(id,personJPA);
        //act
        PersonJPA result = accountIDJPA.getPersonJPA();
        //assert
        assertEquals(personJPA,result);
    }
}