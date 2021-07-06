package switchtwentytwenty.project.interfaceadaptor.implcontroller.family;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.IFamilyAndMemberService;
import switchtwentytwenty.project.applicationservice.irepository.IFamilyRepository;
import switchtwentytwenty.project.applicationservice.irepository.IPersonRepository;
import switchtwentytwenty.project.autentication.UserRepository;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata.*;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.indto.FamilyRelationInDTO;
import switchtwentytwenty.project.dto.outdto.ViewRelationOutDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;
import switchtwentytwenty.project.dto.toservicedto.FamilyAndAdminDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.icontroller.relation.ICreateOrUpdateFamilyRelationController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest()
class CreateOrUpdateFamilyRelationControllerIT {

    @Autowired
    IFamilyAndMemberService service;

    @Autowired
    IPersonRepository personRepository;

    @Autowired
    IFamilyRepository familyRepository;

    @Autowired
    ICreateOrUpdateFamilyRelationController controller;

    @Autowired
    UserRepository userRepository;


    @BeforeEach
    public void before() throws SQLException {
        personRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    @DisplayName("Create a family relation - success")
    void createFamilyRelationSuccess() throws Exception {
        //arrange

        //create family
        String adminEmail = "margaret_hamilton@gmail.com";
        Email adminID = new Email(adminEmail);
        String username = "Maggy";
        String password = "1234";

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "213025086", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", phoneNumbers, adminEmail, "Hamilton", username, password);

        service.startFamily(dto);

        Person admin = personRepository.findByID(adminID);
        FamilyID familyID = admin.getFamilyID();

        //add new member
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658542"));
        Address address = new Address("Rua Velha", "2", "4125-100", "Porto", "Portugal");

        String kinEmail = "john_von_neumann@hotmail.com";

        PersonVoDTO voPersonDTO = new PersonVoDTO(
                new PersonName("Johnny"),
                new BirthDate("1907-06-08"),
                new VAT("286710455"),
                address,
                telephoneNumberList,
                new Email(kinEmail),
                familyID,
                new LedgerID(UUID.randomUUID()));
        Person person = PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        int expectedCode = 201;
        int expectedListSize = 1;
        int resultListSize;

        FamilyRelationInDTO info = new FamilyRelationInDTO(adminEmail, kinEmail, Constants.CHILD);

        //act
        ResponseEntity<Object> result = controller.createOrUpdateFamilyRelation(familyID.toString(), info);

        Family family = familyRepository.findByID(familyID);
        List<FamilyRelation> familyRelationList = family.getFamilyRelationByPersonID(adminID);
        resultListSize = familyRelationList.size();

        //assert
        assertEquals(expectedCode, result.getStatusCodeValue());
        assertEquals(expectedListSize, resultListSize);
    }


    @Test
    @DisplayName("Create a family relation - twice the same exact relation")
    void createFamilyRelationTwiceSameExactRelation() throws Exception {
        //arrange

        //create family
        String adminEmail = "margaret_hamilton@gmail.com";
        Email adminID = new Email(adminEmail);
        String username = "Maggy";
        String password = "1234";

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "213025086", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", phoneNumbers, adminEmail, "Hamilton", username, password);

        service.startFamily(dto);

        Person admin = personRepository.findByID(adminID);
        FamilyID familyID = admin.getFamilyID();

        //add new member
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658542"));
        Address address = new Address("Rua Velha", "2", "4125-100", "Porto", "Portugal");

        String kinEmail = "john_von_neumann@hotmail.com";

        PersonVoDTO voPersonDTO = new PersonVoDTO(
                new PersonName("Johnny"),
                new BirthDate("1907-06-08"),
                new VAT("286710455"),
                address,
                telephoneNumberList,
                new Email(kinEmail),
                familyID,
                new LedgerID(UUID.randomUUID()));
        Person person = PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        int expectedCode = 201;
        int expectedListSize = 1;
        int resultListSize;

        FamilyRelationInDTO info = new FamilyRelationInDTO(adminEmail, kinEmail, Constants.CHILD);

        //act
        controller.createOrUpdateFamilyRelation(familyID.toString(), info);
        ResponseEntity<Object> result = controller.createOrUpdateFamilyRelation(familyID.toString(), info);

        ViewRelationOutDTO outDTO = service.getFamilyRelationByPersonID(adminID.toString());
        resultListSize = outDTO.listSize();

        //assert
        assertEquals(expectedCode, result.getStatusCodeValue());
        assertEquals(expectedListSize, resultListSize);
    }

    @Test
    @DisplayName("Create a family relation - twice the same relation")
    void createFamilyRelationTwiceSameRelation() throws Exception {
        //arrange

        //create family
        String adminEmail = "margaret_hamilton@gmail.com";
        Email adminID = new Email(adminEmail);
        String username = "Maggy";
        String password = "1234";

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "213025086", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", phoneNumbers, adminEmail, "Hamilton", username, password);

        service.startFamily(dto);

        Person admin = personRepository.findByID(adminID);
        FamilyID familyID = admin.getFamilyID();

        //add new member
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658542"));
        Address address = new Address("Rua Velha", "2", "4125-100", "Porto", "Portugal");

        String kinEmail = "john_von_neumann@hotmail.com";

        PersonVoDTO voPersonDTO = new PersonVoDTO(
                new PersonName("Johnny"),
                new BirthDate("1907-06-08"),
                new VAT("286710455"),
                address,
                telephoneNumberList,
                new Email(kinEmail),
                familyID,
                new LedgerID(UUID.randomUUID()));
        Person person = PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        int expectedCode = 201;
        int expectedListSize = 1;
        int resultListSize;

        FamilyRelationInDTO info = new FamilyRelationInDTO(adminEmail, kinEmail, Constants.CHILD);
        FamilyRelationInDTO otherInfo = new FamilyRelationInDTO(adminEmail, kinEmail, Constants.COUSIN);

        //act
        controller.createOrUpdateFamilyRelation(familyID.toString(), info);
        ResponseEntity<Object> result = controller.createOrUpdateFamilyRelation(familyID.toString(), otherInfo);

        ViewRelationOutDTO outDTO = service.getFamilyRelationByPersonID(adminID.toString());
        resultListSize = outDTO.listSize();

        //assert
        assertEquals(expectedCode, result.getStatusCodeValue());
        assertEquals(expectedListSize, resultListSize);
    }

    @Test
    @DisplayName("Create a family relation - two members of different families")
    void createFamilyRelationTwoMembersOfDifferentFamilies() throws InvalidDateException, InvalidEmailException, PersonAlreadyInSystemException, InvalidVATException, InvalidPersonNameException, ElementNotFoundException, BusinessErrorMessage {
        //arrange

        //create family I
        String adminEmailOne = "margaret_hamilton@gmail.com";
        Email adminIDOne = new Email(adminEmailOne);
        String username1 = "Maggy";
        String password1 = "1234";

        List<String> phoneNumbersOne = new ArrayList<>();
        phoneNumbersOne.add("912343546");
        FamilyAndAdminDTO dtoOne = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "213025086", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", phoneNumbersOne, adminEmailOne, "Hamilton", username1, password1);

        service.startFamily(dtoOne);

        Person adminOne = personRepository.findByID(adminIDOne);
        FamilyID familyIDOne = adminOne.getFamilyID();

        //create family II
        String adminEmailTwo = "alan_turing@gmail.com";
        String username2 = "Allan";
        String password2 = "1234";

        List<String> phoneNumbersTwo = new ArrayList<>();
        phoneNumbersTwo.add("912343546");
        FamilyAndAdminDTO dtoTwo = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "213025086", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", phoneNumbersTwo, adminEmailTwo, "Hamilton", username2, password2);

        service.startFamily(dtoTwo);

        //create family relation

        int expectedCode = 400;

        FamilyRelationInDTO info = new FamilyRelationInDTO(adminEmailOne, adminEmailTwo, Constants.CHILD);

        //act
        ResponseEntity<Object> result = controller.createOrUpdateFamilyRelation(familyIDOne.toString(), info);

        //assert
        assertEquals(expectedCode, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Create a family relation - two family relations")
    void createFamilyRelationTwoRelations() throws Exception {
        //arrange

        //create family
        String adminEmail = "margaret_hamilton@gmail.com";
        Email adminID = new Email(adminEmail);
        String username = "Allan";
        String password = "1234";

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "213025086", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", phoneNumbers, adminEmail, "Hamilton", username, password);

        service.startFamily(dto);

        Person admin = personRepository.findByID(adminID);
        FamilyID familyID = admin.getFamilyID();

        //add new member
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658542"));
        Address address = new Address("Rua Velha", "2", "4125-100", "Porto", "Portugal");

        String kinEmail = "john_von_neumann@hotmail.com";

        PersonVoDTO voPersonDTO1 = new PersonVoDTO(
                new PersonName("Johnny"),
                new BirthDate("1907-06-08"),
                new VAT("286710455"),
                address,
                telephoneNumberList,
                new Email(kinEmail),
                familyID,
                new LedgerID(UUID.randomUUID()));
        Person person1 = PersonFactory.create(voPersonDTO1);

        personRepository.save(person1);

        String otherKinEmail = "alan_turing@hotmail.com";

        PersonVoDTO voPersonDTO2 = new PersonVoDTO(
                new PersonName("Alan"),
                new BirthDate("1901-02-01"),
                new VAT("255431686"),
                address,
                telephoneNumberList,
                new Email(otherKinEmail),
                familyID,
                new LedgerID(UUID.randomUUID()));
        Person person2 = PersonFactory.create(voPersonDTO2);
        personRepository.save(person2);

        int expectedCode = 201;
        int expectedListSize = 2;
        int resultListSize;

        FamilyRelationInDTO info = new FamilyRelationInDTO(adminEmail, kinEmail, Constants.CHILD);
        FamilyRelationInDTO otherInfo = new FamilyRelationInDTO(adminEmail, otherKinEmail, Constants.COUSIN);

        //act
        controller.createOrUpdateFamilyRelation(familyID.toString(), info);
        ResponseEntity<Object> result = controller.createOrUpdateFamilyRelation(familyID.toString(), otherInfo);

        Family family = familyRepository.findByID(familyID);

        List<FamilyRelation> familyRelationList = family.getFamilyRelationByPersonID(adminID);
        resultListSize = familyRelationList.size();

        //assert
        assertEquals(expectedCode, result.getStatusCodeValue());
        assertEquals(expectedListSize, resultListSize);
    }

    @Test
    @DisplayName("Create a family relation - illegal relation type")
    void createFamilyRelationIllegalRelation() throws Exception {
        //arrange

        //create family
        String adminEmail = "margaret_hamilton@gmail.com";
        Email adminID = new Email(adminEmail);
        String username = "Maggy";
        String password = "1234";

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "213025086", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", phoneNumbers, adminEmail, "Hamilton", username, password);

        service.startFamily(dto);

        Person admin = personRepository.findByID(adminID);
        FamilyID familyID = admin.getFamilyID();

        //add new member
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658542"));
        Address address = new Address("Rua Velha", "2", "4125-100", "Porto", "Portugal");

        String kinEmail = "john_von_neumann@hotmail.com";

        PersonVoDTO voPersonDTO = new PersonVoDTO(
                new PersonName("Johnny"),
                new BirthDate("1907-06-08"),
                new VAT("286710455"),
                address,
                telephoneNumberList,
                new Email(kinEmail),
                familyID,
                new LedgerID(UUID.randomUUID()));
        Person person = PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        int expectedCode = 400;
        int expectedSize = 0;
        int resultSize;

        FamilyRelationInDTO info = new FamilyRelationInDTO(adminEmail, kinEmail, "Mistress");

        //act
        ResponseEntity<Object> result = controller.createOrUpdateFamilyRelation(familyID.toString(), info);

        Family family = familyRepository.findByID(familyID);

        List<FamilyRelation> familyRelationList = family.getFamilyRelationByPersonID(adminID);
        resultSize = familyRelationList.size();

        //assert
        assertEquals(expectedCode, result.getStatusCodeValue());
        assertEquals(resultSize, expectedSize);
    }

    @Test
    @DisplayName("Create a family relation - same person")
    void createFamilyRelationSamePerson() throws Exception {
        //arrange

        //create family
        String adminEmail = "margaret_hamilton@gmail.com";
        Email adminID = new Email(adminEmail);
        String username = "Maggy";
        String password = "1234";

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "213025086", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", phoneNumbers, adminEmail, "Hamilton", username, password);

        service.startFamily(dto);

        Person admin = personRepository.findByID(adminID);
        FamilyID familyID = admin.getFamilyID();

        int expectedCode = 400;
        int expectedSize = 0;
        int resultSize;

        FamilyRelationInDTO info = new FamilyRelationInDTO(adminEmail, adminEmail, Constants.CHILD);

        //act
        ResponseEntity<Object> result = controller.createOrUpdateFamilyRelation(familyID.toString(), info);

        Family family = familyRepository.findByID(familyID);

        List<FamilyRelation> familyRelationList = family.getFamilyRelationByPersonID(adminID);
        resultSize = familyRelationList.size();

        //assert
        assertEquals(expectedCode, result.getStatusCodeValue());
        assertEquals(resultSize, expectedSize);
    }

    @Test
    @DisplayName("Create a family relation - different families")
    void createFamilyRelationDifferentFamilies() throws Exception {
        //arrange

        //create family
        String adminEmail = "margaret_hamilton@gmail.com";
        Email adminID = new Email(adminEmail);
        String username = "Maggy";
        String password = "1234";

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "213025086", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", phoneNumbers, adminEmail, "Hamilton", username, password);

        service.startFamily(dto);

        Person admin = personRepository.findByID(adminID);
        FamilyID familyID = admin.getFamilyID();

        //add new member
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658542"));
        Address address = new Address("Rua Velha", "2", "4125-100", "Porto", "Portugal");

        String kinEmail = "john_von_neumann@hotmail.com";

        PersonVoDTO voPersonDTO = new PersonVoDTO(
                new PersonName("Johnny"),
                new BirthDate("1907-06-08"),
                new VAT("286710455"),
                address,
                telephoneNumberList,
                new Email(kinEmail),
                familyID,
                new LedgerID(UUID.randomUUID()));
        Person person = PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        FamilyID otherFamilyID = new FamilyID(UUID.randomUUID());

        int expectedCode = 400;
        int expectedSize = 0;
        int resultSize;

        FamilyRelationInDTO info = new FamilyRelationInDTO(adminEmail, kinEmail, Constants.CHILD);

        //act
        ResponseEntity<Object> result = controller.createOrUpdateFamilyRelation(otherFamilyID.toString(), info);

        Family family = familyRepository.findByID(familyID);

        List<FamilyRelation> familyRelationList = family.getFamilyRelationByPersonID(adminID);
        resultSize = familyRelationList.size();

        //assert
        assertEquals(expectedCode, result.getStatusCodeValue());
        assertEquals(resultSize, expectedSize);
    }

    @Test
    @DisplayName("Update a family relation - change kin position")
    void updateFamilyRelationChangePositions() throws Exception {
        //arrange

        //create family
        String adminEmail = "margaret_hamilton@gmail.com";
        Email adminID = new Email(adminEmail);
        String username = "Maggy";
        String password = "1234";

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "213025086", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", phoneNumbers, adminEmail, "Hamilton", username, password);

        service.startFamily(dto);

        Person admin = personRepository.findByID(adminID);
        FamilyID familyID = admin.getFamilyID();

        //add new member
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658542"));
        Address address = new Address("Rua Velha", "2", "4125-100", "Porto", "Portugal");

        String kinEmail = "john_von_neumann@hotmail.com";

        PersonVoDTO voPersonDTO = new PersonVoDTO(
                new PersonName("Johnny"),
                new BirthDate("1907-06-08"),
                new VAT("286710455"),
                address,
                telephoneNumberList,
                new Email(kinEmail),
                familyID,
                new LedgerID(UUID.randomUUID()));
        Person person = PersonFactory.create(voPersonDTO);
        personRepository.save(person);

        int expectedCode = 201;
        int expectedListSize = 1;
        int resultListSize;

        FamilyRelationInDTO info = new FamilyRelationInDTO(adminEmail, kinEmail, Constants.CHILD);
        FamilyRelationInDTO otherInfo = new FamilyRelationInDTO(kinEmail, adminEmail, Constants.COUSIN);

        //act
        controller.createOrUpdateFamilyRelation(familyID.toString(), info);
        ResponseEntity<Object> result = controller.createOrUpdateFamilyRelation(familyID.toString(), otherInfo);

        Family family = familyRepository.findByID(familyID);

        List<FamilyRelation> familyRelationList = family.getFamilyRelationByPersonID(adminID);
        resultListSize = familyRelationList.size();

        //assert
        assertEquals(expectedCode, result.getStatusCodeValue());
        assertEquals(expectedListSize, resultListSize);
    }

    @Test
    @DisplayName("Create a family relation - not existent email")
    void createFamilyRelationNotExistentEmail() throws Exception {
        //arrange

        //create family
        String adminEmail = "margaret_hamilton@gmail.com";
        Email adminID = new Email(adminEmail);
        String username = "Maggy";
        String password = "1234";

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "213025086", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", phoneNumbers, adminEmail, "Hamilton", username, password);

        service.startFamily(dto);

        Person admin = personRepository.findByID(adminID);
        FamilyID familyID = admin.getFamilyID();

        String fakeEmail = "fake@gmail.com";

        FamilyRelationInDTO info = new FamilyRelationInDTO(adminEmail, fakeEmail, Constants.CHILD);

        //act and assert
        assertThrows(ElementNotFoundException.class, () -> {
            controller.createOrUpdateFamilyRelation(familyID.toString(), info);
        });
    }

}