package switchtwentytwenty.project.interfaceadaptor.implcontroller.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.autentication.UserRepository;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata.BirthDate;
import switchtwentytwenty.project.domain.share.persondata.PersonName;
import switchtwentytwenty.project.domain.share.persondata.TelephoneNumberList;
import switchtwentytwenty.project.domain.share.persondata.VAT;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.indto.PersonInDTO;
import switchtwentytwenty.project.dto.outdto.PersonOutDTO;
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AddFamilyMemberControllerIT {

    @Autowired
    AddFamilyMemberController controller;
    @Autowired
    IFamilyRepository familyRepository;
    @Autowired
    IPersonRepository personRepository;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void before(){
        personRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Add family member successfully")
    void testAddMemberSuccessfully()
            throws Exception {
        //arrange
        //create family
        FamilyVoDTO familyDTO = new FamilyVoDTO(new FamilyID(UUID.randomUUID()), new LedgerID(UUID.randomUUID()), new Email("admin@gmail.com"),
                new FamilyName("Costa"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        String familyID = family.getID().toString();
        //create dto with person data
        String email = "yeah@gmail.com";
        String name = "Constantino";
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("912654986");
        PersonInDTO dto = new PersonInDTO();
        dto.setEmail(email);
        dto.setName(name);
        dto.setBirthDate("2020-01-01");
        dto.setVat("123456789");
        dto.setHouseNumber("25");
        dto.setStreet("Rua Nova");
        dto.setCity("Porto");
        dto.setCountry("Portugal");
        dto.setZipCode("2156-958");
        dto.setPhoneNumbers(phoneNumbers);
        dto.setFamilyID(familyID);
        dto.setUsername("constantino");
        dto.setPassword("constantino");
        //create expected out dto
        PersonOutDTO expectedDTO = new PersonOutDTO(name, email, familyID);
        //act
        ResponseEntity<Object> response = controller.addFamilyMember(dto);
        HttpStatus resultStatus = response.getStatusCode();
        Object resultDTO = response.getBody();
        //assert
        assertEquals(HttpStatus.CREATED, resultStatus);
        assertEquals(expectedDTO, resultDTO);
    }

    @Test
    @DisplayName("Add family member successfully: without telephone numbers")
    void testAddMemberSuccessfully_WithoutTelephoneNumbers()
            throws Exception {
        //arrange
        //create family
        FamilyVoDTO familyDTO = new FamilyVoDTO(new FamilyID(UUID.randomUUID()), new LedgerID(UUID.randomUUID()), new Email("admin@gmail.com"),
                new FamilyName("Costa"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        String familyID = family.getID().toString();
        //create dto with person data
        String email = "yeah@gmail.com";
        String name = "Constantino";
        PersonInDTO dto = new PersonInDTO();
        dto.setEmail(email);
        dto.setName(name);
        dto.setBirthDate("2020-01-01");
        dto.setVat("123456789");
        dto.setHouseNumber("25");
        dto.setStreet("Rua Nova");
        dto.setCity("Porto");
        dto.setCountry("Portugal");
        dto.setZipCode("2156-958");
        dto.setFamilyID(familyID);
        dto.setUsername("constantino");
        dto.setPassword("constantino");
        //create expected out dto
        PersonOutDTO expectedDTO = new PersonOutDTO(name, email, familyID);
        //act
        ResponseEntity<Object> response = controller.addFamilyMember(dto);
        HttpStatus resultStatus = response.getStatusCode();
        Object resultDTO = response.getBody();
        //assert
        assertEquals(HttpStatus.CREATED, resultStatus);
        assertEquals(expectedDTO, resultDTO);
    }

    @Test
    @DisplayName("Failure add family member: family not found")
    void failureAddFamilyMember_FamilyNotFound() {
        //arrange
        String familyID = UUID.randomUUID().toString();
        //create dto with person data
        PersonInDTO dto = new PersonInDTO();
        dto.setEmail("yeah@gmail.com");
        dto.setName("Constantino");
        dto.setBirthDate("2020-01-01");
        dto.setVat("123456789");
        dto.setHouseNumber("25");
        dto.setStreet("Rua Nova");
        dto.setCity("Porto");
        dto.setCountry("Portugal");
        dto.setZipCode("2156-958");
        dto.setFamilyID(familyID);
        dto.setUsername("constantino");
        dto.setPassword("constantino");
        //act
        ResponseEntity<Object> response = controller.addFamilyMember(dto);
        HttpStatus resultStatus = response.getStatusCode();
        //assert
        assertEquals(HttpStatus.BAD_REQUEST, resultStatus);
    }

    @Test
    @DisplayName("Failure add family member: invalid person name")
    void failureAddFamilyMember_InvalidPersonName()
            throws Exception {
        //arrange
        //create family
        FamilyVoDTO familyDTO = new FamilyVoDTO(new FamilyID(UUID.randomUUID()), new LedgerID(UUID.randomUUID()), new Email("admin@gmail.com"),
                new FamilyName("Costa"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        String familyID = family.getID().toString();
        //create dto with person data
        PersonInDTO dto = new PersonInDTO();
        dto.setEmail("yeah@gmail.com");
        dto.setName("123");
        dto.setBirthDate("2020-01-01");
        dto.setVat("123456789");
        dto.setHouseNumber("25");
        dto.setStreet("Rua Nova");
        dto.setCity("Porto");
        dto.setCountry("Portugal");
        dto.setZipCode("2156-958");
        dto.setFamilyID(familyID);
        dto.setUsername("constantino");
        dto.setPassword("constantino");
        //act
        ResponseEntity<Object> response = controller.addFamilyMember(dto);
        HttpStatus resultStatus = response.getStatusCode();
        //assert
        assertEquals(HttpStatus.BAD_REQUEST, resultStatus);
    }


    @Test
    @DisplayName("Failure add family member: person already in system")
    void failureAddFamilyMember_PersonAlreadyInSystem() throws Exception {
        //arrange
        FamilyVoDTO familyDTO = new FamilyVoDTO(new FamilyID(UUID.randomUUID()), new LedgerID(UUID.randomUUID()), new Email("admin@gmail.com"),
                new FamilyName("Costa"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        String familyID = family.getID().toString();
        String email = "yeah@gmail.com";
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        //create and save member
        PersonVoDTO personVoDTO = new PersonVoDTO(
                new PersonName("Joaquina"),
                new BirthDate("2020-03-02"),
                new VAT("123456789"),
                new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal"),
                telephoneNumberList,
                new Email(email),
                new FamilyID(UUID.fromString(familyID)),
                new LedgerID(UUID.randomUUID()));
        Person member = PersonFactory.create(personVoDTO);
        personRepository.save(member);
        //create dto with person data
        PersonInDTO dto = new PersonInDTO();
        dto.setEmail(email);
        dto.setName("Joaquim");
        dto.setBirthDate("2020-01-01");
        dto.setVat("123456789");
        dto.setHouseNumber("25");
        dto.setStreet("Rua Nova");
        dto.setCity("Porto");
        dto.setCountry("Portugal");
        dto.setZipCode("2156-958");
        dto.setFamilyID(familyID);
        dto.setUsername("joaquim");
        dto.setPassword("joaquim");
        //act
        ResponseEntity<Object> response = controller.addFamilyMember(dto);
        HttpStatus resultStatus = response.getStatusCode();
        //assert
        assertEquals(HttpStatus.BAD_REQUEST, resultStatus);
    }

    @Test
    @DisplayName("Failure add family member: vat already used in family")
    void failureAddFamilyMember_VatAlreadyUsedInFamily()
            throws Exception {
        //arrange
        FamilyVoDTO familyDTO = new FamilyVoDTO(new FamilyID(UUID.randomUUID()), new LedgerID(UUID.randomUUID()), new Email("admin@gmail.com"),
                new FamilyName("Costa"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        String familyID = family.getID().toString();
        String email = "yeah@gmail.com";
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        //create and save member
        PersonVoDTO personVoDTO = new PersonVoDTO(
                new PersonName("Joaquina"),
                new BirthDate("2020-03-02"),
                new VAT("123456789"),
                new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal"),
                telephoneNumberList,
                new Email(email),
                new FamilyID(UUID.fromString(familyID)),
                new LedgerID(UUID.randomUUID()));
        Person member = PersonFactory.create(personVoDTO);
        personRepository.save(member);
        //create dto with person data
        PersonInDTO dto = new PersonInDTO();
        dto.setEmail("otherEmail@gmail.com");
        dto.setName("Joaquim");
        dto.setBirthDate("2020-01-01");
        dto.setVat("123456789");
        dto.setHouseNumber("25");
        dto.setStreet("Rua Nova");
        dto.setCity("Porto");
        dto.setCountry("Portugal");
        dto.setZipCode("2156-958");
        dto.setFamilyID(familyID);
        dto.setUsername("joaquim");
        dto.setPassword("joaquim");
        //act
        ResponseEntity<Object> response = controller.addFamilyMember(dto);
        HttpStatus resultStatus = response.getStatusCode();
        //assert
        assertEquals(HttpStatus.BAD_REQUEST, resultStatus);
    }
}

