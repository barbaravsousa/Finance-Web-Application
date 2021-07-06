package switchtwentytwenty.project.datamodel.assembler;

import org.junit.jupiter.api.Test;
import switchtwentytwenty.project.datamodel.PersonJPA;
import switchtwentytwenty.project.datamodel.TelephoneNumberJPA;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumber;

import static org.junit.jupiter.api.Assertions.*;

class TelephoneNumberDomainAssemblerTest {

    @Test
    void toDomain(){
        PersonJPA personJPA = new PersonJPA();
        String number = "928785678";
        //arrange
        TelephoneNumberJPA jpa = new TelephoneNumberJPA(number,personJPA);
        //act
        TelephoneNumber telephoneNumber = TelephoneNumberDomainAssembler.toDomain(jpa);
        //assert
        assertNotNull(telephoneNumber);
        assertNotNull(jpa.getPersonJPA());
    }

    @Test
    void toData(){
        PersonJPA personJPA = new PersonJPA();
        String number = "928785678";
        //arrange
        TelephoneNumber telephoneNumber = new TelephoneNumber(number);
        //act
        TelephoneNumberJPA jpa = TelephoneNumberDomainAssembler.toData(telephoneNumber,personJPA);
        //assert
        assertNotNull(jpa);
    }
}