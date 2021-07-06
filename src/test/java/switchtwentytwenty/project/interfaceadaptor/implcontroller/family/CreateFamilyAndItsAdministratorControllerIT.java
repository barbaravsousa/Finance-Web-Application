package switchtwentytwenty.project.interfaceadaptor.implcontroller.family;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.implappservice.FamilyAndMemberService;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.applicationservice.irepository.ILedgerRepository;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.dto.indto.FamilyAndAdminInDTO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreateFamilyAndItsAdministratorControllerIT {

    @Autowired      //for integration tests
    FamilyAndMemberService service;
    @Autowired
    CreateFamilyAndItsAdministratorController controller;
    @Autowired
    IFamilyRepository familyRepository;
    @Autowired
    IPersonRepository personRepository;
    @Autowired
    ILedgerRepository ledgerRepository;

    @BeforeEach
    public void before(){
        personRepository.deleteAll();
        familyRepository.deleteAll();
    }

    @Test
    @DisplayName("Create family and administrator successfully")
    void startFamilySuccessfully1() throws Exception{
        //arrange
        FamilyAndAdminInDTO dto = new FamilyAndAdminInDTO();
        dto.setBirthDate("1998-12-13");
        dto.setCity("Porto");
        dto.setCountry("Portugal");
        dto.setPersonName("Indy");
        dto.setEmail("indy98@gmail.com");
        dto.setFamilyName("Jones");
        dto.setHouseNumber("23");
        dto.setStreet("Dragons steet");
        dto.setVat("257069313");
        dto.setZipCode("2345-987");
        dto.setDescription("addfamily");
        dto.setUsername("jones98");
        dto.setPassword("jones98");
        Email adminID = new Email(dto.getEmail());
        //act
        ResponseEntity<Object> result = controller.startFamily(dto);
        //assert
        assertEquals(201, result.getStatusCodeValue());
        Person person = personRepository.findByID(adminID);
        FamilyID familyID = person.getFamilyID();
        Family family = familyRepository.findByID(familyID);
        LedgerID personLedgerID = person.getLedgerID();
        LedgerID familyLedgerID = family.getLedgerID();
        Ledger personLedger = ledgerRepository.findByID(personLedgerID);
        Ledger familyLedger = ledgerRepository.findByID(familyLedgerID);
        assertNotNull(personLedger);
        assertNotNull(familyLedger);
    }

    @Test
    @DisplayName("Create family and administrator successfully")
    void startFamilySuccessfully2() throws Exception{
        //arrange
        FamilyAndAdminInDTO dto = new FamilyAndAdminInDTO();
        dto.setBirthDate("1998-12-13");
        dto.setCity("Porto");
        dto.setCountry("Portugal");
        dto.setPersonName("Indy");
        dto.setEmail("indy56@gmail.com");
        dto.setFamilyName("Jones");
        dto.setHouseNumber("23");
        dto.setStreet("Dragons steet");
        dto.setVat("257069313");
        dto.setZipCode("2345-987");
        dto.setDescription("addfamily");
        dto.setUsername("indy67jones");
        dto.setPassword("indyjones945");
        Email adminID = new Email(dto.getEmail());
        //act
        ResponseEntity<Object> result = controller.startFamily(dto);
        //assert
        assertEquals(201, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Unsuccessful method: invalid family name")
    void startFamily_invalidEmail() {
        //arrange
        FamilyAndAdminInDTO dto = new FamilyAndAdminInDTO();
        dto.setBirthDate("1998-12-13");
        dto.setCity("Porto");
        dto.setCountry("Portugal");
        dto.setPersonName("Indy");
        dto.setEmail("indygmail.com");
        dto.setFamilyName("Jones");
        dto.setHouseNumber("23");
        dto.setStreet("Dragons steet");
        dto.setVat("257069313");
        dto.setZipCode("2345-987");
        dto.setDescription("addfamily");
        //act
        ResponseEntity<Object> result = controller.startFamily(dto);
        //assert
        assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Unsuccessful method: invalid person birthDate")
    void startFamily_administratorInvalidBirthDate() {
        //arrange
        FamilyAndAdminInDTO dto = new FamilyAndAdminInDTO();
        dto.setBirthDate("1998-14-13");
        dto.setCity("Porto");
        dto.setCountry("Portugal");
        dto.setPersonName("Indy");
        dto.setEmail("indy@gmail.com");
        dto.setFamilyName("Jones");
        dto.setHouseNumber("23");
        dto.setStreet("Dragons steet");
        dto.setVat("257069313");
        dto.setZipCode("2345-987");
        dto.setDescription("addfamily");
        //act
        ResponseEntity<Object> result = controller.startFamily(dto);
        //assert
        assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Unsuccessful method: invalid vat")
    void startFamily_administratorInvalidVat() {
        //arrange
        FamilyAndAdminInDTO dto = new FamilyAndAdminInDTO();
        dto.setBirthDate("1998-10-13");
        dto.setCity("Porto");
        dto.setCountry("Portugal");
        dto.setPersonName("Indy");
        dto.setEmail("indy@gmail.com");
        dto.setFamilyName("Jones");
        dto.setHouseNumber("23");
        dto.setStreet("Dragons steet");
        dto.setVat("25706931");
        dto.setZipCode("2345-987");
        dto.setDescription("addfamily");
        //act
        ResponseEntity<Object> result = controller.startFamily(dto);
        //assert
        assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Unsuccessful method: invalid person email - already used")
    void startFamily_administratorInvalidID() {
        //arrange
            //family bones
        FamilyAndAdminInDTO jones = new FamilyAndAdminInDTO();
        jones.setBirthDate("1965-11-13");
        jones.setCity("Porto");
        jones.setPersonName("Indy");
        jones.setCountry("Portugal");
        jones.setEmail("indy88@gmail.com");
        jones.setFamilyName("Jones");
        jones.setHouseNumber("23");
        jones.setStreet("Dragons steet");
        jones.setVat("254190235");
        jones.setZipCode("2345-987");
        jones.setDescription("add2family");
        jones.setUsername("jones88");
        jones.setPassword("jones88");
            //family adams
        FamilyAndAdminInDTO adam = new FamilyAndAdminInDTO();
        adam.setBirthDate("1965-11-13");
        adam.setCity("Porto");
        adam.setPersonName("Indy");
        adam.setCountry("Portugal");
        adam.setEmail("indy88@gmail.com");
        adam.setFamilyName("Jones");
        adam.setHouseNumber("23");
        adam.setStreet("Dragons steet");
        adam.setVat("257069313");
        adam.setZipCode("2345-987");
        adam.setDescription("addfamily");
        adam.setUsername("adam88");
        adam.setPassword("adam88");
        //act
        ResponseEntity<Object> jonesResult = controller.startFamily(jones);
        ResponseEntity<Object> adamResult = controller.startFamily(adam);
        //assert
        assertEquals(201, jonesResult.getStatusCodeValue());
        assertEquals(400, adamResult.getStatusCodeValue());
    }

    @Test
    @DisplayName("Add second family with the same name")
    void startSecondFamilySuccessfully() {
        //arrange
            //family bones
        List<String> jonesNumber = new ArrayList<>();
        jonesNumber.add("912343546");
        //family bones
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("912343546");
        FamilyAndAdminInDTO jones = new FamilyAndAdminInDTO();
        jones.setBirthDate("1965-11-13");
        jones.setCity("Porto");
        jones.setPersonName("Indy");
        jones.setCountry("Portugal");
        jones.setEmail("indy@gmail.com");
        jones.setFamilyName("Jones");
        jones.setHouseNumber("23");
        jones.setStreet("Dragons steet");
        jones.setVat("257069313");
        jones.setZipCode("2345-987");
        jones.setDescription("addfamily");
        jones.setPhoneNumbers(jonesNumber);
        jones.setUsername("jones");
        jones.setPassword("jones95");
            //family adams
        FamilyAndAdminInDTO adam = new FamilyAndAdminInDTO();
        adam.setBirthDate("1965-11-13");
        adam.setCity("Porto");
        adam.setPersonName("Indy");
        adam.setCountry("Portugal");
        adam.setEmail("adams@gmail.com");
        adam.setFamilyName("Adams");
        adam.setHouseNumber("23");
        adam.setStreet("Dragons steet");
        adam.setVat("257069313");
        adam.setZipCode("2345-987");
        adam.setDescription("addfamily");
        adam.setUsername("adam");
        adam.setPassword("adam95");
        //act
        ResponseEntity<Object> jonesResult = controller.startFamily(jones);
        ResponseEntity<Object> adamResult = controller.startFamily(adam);
        //assert
        assertEquals(201, jonesResult.getStatusCodeValue());
        assertEquals(201, adamResult.getStatusCodeValue());
    }

    @Test
    @DisplayName("Unsuccessful method: invalid person city (null)")
    void startFamily_administratorInvalidCity() {
        //arrange
        FamilyAndAdminInDTO jones = new FamilyAndAdminInDTO();
        jones.setBirthDate("1965-11-13");
        jones.setPersonName("Indy");
        jones.setCountry("Portugal");
        jones.setEmail("indy@gmail.com");
        jones.setFamilyName("Jones");
        jones.setHouseNumber("23");
        jones.setStreet("Dragons steet");
        jones.setVat("257069313");
        jones.setZipCode("2345-987");
        jones.setDescription("addfamily");
        //act
        ResponseEntity<Object> jonesResult = controller.startFamily(jones);
        //assert
        assertEquals(400, jonesResult.getStatusCodeValue());
    }
}


