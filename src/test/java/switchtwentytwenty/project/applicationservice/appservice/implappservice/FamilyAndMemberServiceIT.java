package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;
import switchtwentytwenty.project.autentication.UserRepository;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;
import switchtwentytwenty.project.domain.share.familydata.RegistrationDate;
import switchtwentytwenty.project.domain.share.familydata.RelationType;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.persondata.*;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.outdto.ViewRelationOutDTO;
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;
import switchtwentytwenty.project.dto.toservicedto.FamilyAndAdminDTO;
import switchtwentytwenty.project.dto.toservicedto.PersonDTO;
import switchtwentytwenty.project.exception.*;
import switchtwentytwenty.project.interfaceadaptor.repository.FamilyRepository;
import switchtwentytwenty.project.interfaceadaptor.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
class FamilyAndMemberServiceIT {
    @Autowired
    FamilyRepository familyRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    FamilyAndMemberService familyAndMemberService;
    @Autowired
    AccountService accountService;
    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void before() {
        personRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Get an empty family relation list from a person if the person has no relations")
    void getAnEmptyFamilyRelationListFromAPerson() throws Exception {
        //arrange
        FamilyName familyNameSimpson = new FamilyName("Simpson");
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("220058942"));
        Email adminEmailSimpson = new Email("homer_simpson@gmail.com");
        Address address = new Address("Springfield", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1961-01-05");
        PersonName name = new PersonName("Homer Simpson");
        VAT vat = new VAT("245014314");

        PersonVoDTO personVoDTO = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, adminEmailSimpson, familyID,
                new LedgerID(UUID.randomUUID()));
        Person person = PersonFactory.create(personVoDTO);
        personRepository.save(person);
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, new LedgerID(UUID.randomUUID()), adminEmailSimpson, familyNameSimpson);
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //act
        int result = familyAndMemberService.getFamilyRelationByPersonID("homer_simpson@gmail.com").listSize();
        int expected = 0;

        // assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get family relation list from a person")
    void getFamilyRelationListFromAPerson() throws Exception {
        //arrange
        FamilyName familyNameMcFly = new FamilyName("McFly");

        FamilyID familyID = new FamilyID(UUID.randomUUID());

        //Create admin and add to person repository
        TelephoneNumberList martyNumberList = new TelephoneNumberList();
        martyNumberList.add(new TelephoneNumber("225658541"));
        Email adminEmailMcFly = new Email("marty_mcfly@gmail.com");
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate martyBirthDate = new BirthDate("1995-01-22");
        PersonName martyName = new PersonName("Marty McFly");
        VAT martyVat = new VAT("123456789");

        PersonVoDTO personVoDTO = new PersonVoDTO(martyName, martyBirthDate, martyVat, address, martyNumberList, adminEmailMcFly, familyID,
                new LedgerID(UUID.randomUUID()));
        Person marty = PersonFactory.create(personVoDTO);
        personRepository.save(marty);

        //Create family and add to family repository
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, new LedgerID(UUID.randomUUID()), adminEmailMcFly, familyNameMcFly);
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);

        //Add new family members, George and Loraine
        TelephoneNumberList georgeNumberList = new TelephoneNumberList();
        martyNumberList.add(new TelephoneNumber("225658541"));
        Email georgeID = new Email("george_mcfly@sapo.pt");
        BirthDate georgeBirthDate = new BirthDate("1995-01-22");
        PersonName georgeName = new PersonName("George McFly");
        VAT georgeVat = new VAT("299259080");

        PersonVoDTO georgeDTO = new PersonVoDTO(georgeName, georgeBirthDate, georgeVat, address, georgeNumberList, georgeID, familyID,
                new LedgerID(UUID.randomUUID()));
        Person george = PersonFactory.create(georgeDTO);
        personRepository.save(george);

        TelephoneNumberList loraineNumberList = new TelephoneNumberList();
        martyNumberList.add(new TelephoneNumber("225658541"));
        Email loraineID = new Email("loraine_mcfly@sapo.pt");
        BirthDate loraineBirthDate = new BirthDate("1995-01-22");
        PersonName loraineName = new PersonName("Loraine McFly");
        VAT loraineVat = new VAT("251767574");


        PersonVoDTO loraineDTO = new PersonVoDTO(loraineName, loraineBirthDate, loraineVat, address, loraineNumberList, loraineID, familyID,
                new LedgerID(UUID.randomUUID()));
        Person loraine = PersonFactory.create(loraineDTO);
        personRepository.save(loraine);

        RelationType relationMartyGeorge = RelationType.getInstance(Constants.CHILD);
        RelationType relationMartyLoraine = RelationType.getInstance(Constants.CHILD);
        RelationType relationGeorgeLoraine = RelationType.getInstance(Constants.CHILD);

        family.createFamilyRelation(adminEmailMcFly, georgeID, relationMartyGeorge);
        family.createFamilyRelation(loraineID, adminEmailMcFly, relationMartyLoraine);
        family.createFamilyRelation(georgeID, loraineID, relationGeorgeLoraine);

        familyRepository.save(family);

        //act
        ViewRelationOutDTO relationDTO = familyAndMemberService.getFamilyRelationByPersonID("marty_mcfly@gmail.com");
        int result = relationDTO.listSize();
        int expected = 2;

        // assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Create FamilyRelation: null personID")
    void createFamilyRelationNullPersonID() {
        //arrange
        String personIDString = null;
        String kinIDString = "maggy_howard@htomail.com";
        String familyIDString = "F-001";
        String relationTypeName = Constants.PARENT;
        //act and assert
        assertThrows(NullPointerException.class,
                () -> familyAndMemberService.createFamilyRelation(personIDString, kinIDString, familyIDString, relationTypeName));
    }

    @Test
    @DisplayName("Create FamilyRelation: null kinID")
    void createFamilyRelationNullKinID() {
        //arrange
        String personIDString = "maggy_howard@htomail.com";
        String kinIDString = null;
        String familyIDString = "F-001";
        String relationTypeName = Constants.PARENT;
        //act and assert
        assertThrows(NullPointerException.class,
                () -> familyAndMemberService.createFamilyRelation(personIDString, kinIDString, familyIDString, relationTypeName));
    }

    @Test
    @DisplayName("Create FamilyRelation: null familyID")
    void createFamilyRelationNullFamilyID() {
        //arrange
        String personIDString = "maggy_howard@htomail.com";
        String kinIDString = "john_von_neumann@gmail.com";
        String familyIDString = null;
        String relationTypeName = Constants.PARENT;
        //act and assert
        assertThrows(NullPointerException.class,
                () -> familyAndMemberService.createFamilyRelation(personIDString, kinIDString, familyIDString, relationTypeName));
    }

    @Test
    @DisplayName("Create family relation, family not found")
    void familyNotFound() {
        //arrange
        String personID = "alan_turing@hotmail.com";
        String kinID = "john_von_neumann@gmail.com";
        String familyID = UUID.randomUUID().toString();
        //act and assert
        assertThrows(ElementNotFoundException.class, () -> familyAndMemberService.createFamilyRelation(personID, kinID, familyID, Constants.PARENT));
    }

    @Test
    @DisplayName("Create FamilyRelation: null relationType")
    void createFamilyRelationNullRelationType() throws Exception {
        //arrange
        String personID = "maggy_howard@htomail.com";
        String kinID = "john_von_neumann@gmail.com";
        UUID familyID = UUID.randomUUID();
        String s_familyID = familyID.toString();

        TelephoneNumberList telephoneNumbers = new TelephoneNumberList();
        telephoneNumbers.add(new TelephoneNumber("911010100"));
        Address address = new Address("Logical Street", "30", "4100-333", "London", "UK");

        PersonVoDTO personVoDTO = new PersonVoDTO(
                new PersonName("Maggy"),
                new BirthDate("1905-09-08"),
                new VAT("254282601"),
                address,
                telephoneNumbers,
                new Email(personID),
                new FamilyID(familyID),
                new LedgerID(UUID.randomUUID()));
        Person maggy = PersonFactory.create(personVoDTO);
        personRepository.save(maggy);

        PersonVoDTO johnnyDTO = new PersonVoDTO(
                new PersonName("Johnny"),
                new BirthDate("1925-12-08"),
                new VAT("286710455"),
                new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal"),
                telephoneNumbers,
                new Email(kinID),
                new FamilyID(familyID),
                new LedgerID(UUID.randomUUID()));
        Person johnny = PersonFactory.create(johnnyDTO);
        personRepository.save(johnny);

        String relationTypeName = null;

        //act and assert
        assertThrows(NullPointerException.class, () -> familyAndMemberService.createFamilyRelation(personID, kinID, s_familyID, relationTypeName));
    }

    @Test
    @DisplayName("Add family member successfully")
    void addFamilyMemberSuccessfully() throws Exception {
        //arrange
        FamilyVoDTO familyDTO = new FamilyVoDTO(new FamilyID(UUID.randomUUID()), new LedgerID(UUID.randomUUID()), new Email("admin@gmail.com"), new FamilyName("Constantino"));
        Family family = FamilyFactory.create(familyDTO);
        familyRepository.save(family);
        String familyID = family.getID().toString();

        List<String> johnPhoneNumbers = new ArrayList<>();
        johnPhoneNumbers.add("912343546");
        String email = "oli@gmail.com";
        Email personID = new Email(email);
        PersonDTO personDTO = new PersonDTO.PersonDTOBuilder()
                .withName("Ricardo Constantino")
                .withBirthDate("1999-12-31")
                .withCity("Porto")
                .withHouseNumber("1ºEsq")
                .withCountry("Portugal")
                .withStreet("Rua da Boa Vida")
                .withPhoneNumbers(johnPhoneNumbers)
                .withZipCode("9852-634")
                .withVat("123456789")
                .withEmail(email)
                .withFamilyID(familyID)
                .withUsername("Ricky")
                .withPassword("1234")
                .build();
        //act - assert
        assertDoesNotThrow(() -> familyAndMemberService.addFamilyMember(personDTO));
        boolean contains = personRepository.existsByID(personID);
        assertTrue(contains);
    }

    @Test
    // @Transactional
    @DisplayName("Create a family relation successfully")
    void createAFamilyRelationSuccessfully() throws Exception, BusinessErrorMessage {
        //arrange
        int result;
        int expected = 1;

        //create family and administrator
        String adminEmail = "margaret_hamilton@gmail.com";
        Email adminID = new Email(adminEmail);

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "213025086", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", phoneNumbers, adminEmail, "Hamilton", "Maggy", "1234");

        familyAndMemberService.startFamily(dto);

        Person admin = personRepository.findByID(adminID);
        FamilyID familyID = admin.getFamilyID();

        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658542"));
        Address address = new Address("Rua Velha", "2", "4125-100", "Porto", "Portugal");

        String kinID = "john_von_neumann@hotmail.com";

        PersonVoDTO voDTO = new PersonVoDTO(
                new PersonName("Johnny"),
                new BirthDate("1907-06-08"),
                new VAT("286710455"),
                address,
                telephoneNumberList,
                new Email(kinID),
                familyID,
                new LedgerID(UUID.randomUUID()));
        Person person = PersonFactory.create(voDTO);
        personRepository.save(person);

        //act
        familyAndMemberService.createFamilyRelation(adminEmail, kinID, familyID.toString(), Constants.PARENT);
        List<FamilyRelation> familyRelationList = familyAndMemberService.getFamilyRelationByPersonID(adminID, familyID);
        result = familyRelationList.size();

        //assert
        assertEquals(result, expected);
    }

    @Test
    @Transactional
    @DisplayName("Create a family relation - invalid relation type")
    void createAFamilyRelationInvalidRelationType() throws Exception {
        //arrange

        //create family and administrator
        String adminEmail = "margaret_hamilton@gmail.com";
        Email adminID = new Email(adminEmail);

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "213025086", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", phoneNumbers, adminEmail, "Hamilton", "Maggy", "1234");

        familyAndMemberService.startFamily(dto);

        Person admin = personRepository.findByID(adminID);
        FamilyID familyID = admin.getFamilyID();

        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("225658542"));
        Address address = new Address("Rua Velha", "2", "4125-100", "Porto", "Portugal");

        String kinID = "john_von_neumann@hotmail.com";

        PersonVoDTO voDTO = new PersonVoDTO(
                new PersonName("Johnny"),
                new BirthDate("1907-06-08"),
                new VAT("286710455"),
                address,
                telephoneNumberList,
                new Email(kinID),
                familyID,
                new LedgerID(UUID.randomUUID()));
        Person person = PersonFactory.create(voDTO);
        personRepository.save(person);

        //act and assert
        assertThrows(InvalidRelationTypeException.class, () -> familyAndMemberService.createFamilyRelation(adminEmail, kinID, familyID.toString(), "Mistress"));
    }

    @Test
    @DisplayName("Create, retrieve and save family - same system entry date")
    void createRetrieveSaveFamily() throws Exception, BusinessErrorMessage {
        //arrange
        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("912343546");
        phoneNumbers.add("223658965");
        Email admin = new Email("bones@gmail.com");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "213025086", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", phoneNumbers, "bones@gmail.com", "Hamilton", "Maggy", "1234");
        familyAndMemberService.startFamily(dto);
        Person person = personRepository.findByID(admin);
        FamilyID familyID = person.getFamilyID();
        Family family = familyRepository.findByID(familyID);

        RegistrationDate firstRegistrationDate = family.getRegistrationDate();

        //act
        accountService.createFamilyCashAccount(familyID.toString(), admin.toString(), 200, "Family cash account");

        family = familyRepository.findByID(familyID);
        RegistrationDate secondRegistrationDate = family.getRegistrationDate();

        //assert
        assertEquals(firstRegistrationDate, secondRegistrationDate);

    }

    //Create Family Tests

    @Test
    @DisplayName("Create family successfully")
    void startFamilySuccessfully() {
        //arrange
        List<String> johnPhoneNumbers = new ArrayList<>();
        johnPhoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "213025086", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", johnPhoneNumbers, "bones@gmail.com", "Hamilton", "Maggy", "1234");
        //act - assert
        assertDoesNotThrow(() -> familyAndMemberService.startFamily(dto));
    }

    @Test
    @DisplayName("Failure create family: invalid admin vat")
    void startFamilyInvalidAdminVAT() {
        //arrange
        List<String> johnPhoneNumbers = new ArrayList<>();
        johnPhoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1904-12-01", "2130250861", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", johnPhoneNumbers, "bones@gmail.com", "Hamilton", "Maggy", "1234");
        //act - assert
        assertThrows(InvalidVATException.class, () -> familyAndMemberService.startFamily(dto));
    }

    @Test
    @DisplayName("Failure create family: invalid admin birth date")
    void startFamilyInvalidAdminBirthDate() {
        //arrange
        List<String> johnPhoneNumbers = new ArrayList<>();
        johnPhoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1996-12-32", "2130250861", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", johnPhoneNumbers, "bones@gmail.com", "Hamilton", "Maggy", "1234");
        //act - assert
        assertThrows(InvalidDateException.class, () -> familyAndMemberService.startFamily(dto));
    }

    @Test
    @DisplayName("Failure create family: invalid admin email")
    void startFamilyInvalidAdminEmail() {
        //arrange
        List<String> johnPhoneNumbers = new ArrayList<>();
        johnPhoneNumbers.add("912343546");
        FamilyAndAdminDTO dto = new FamilyAndAdminDTO("Margaret Hamilton", "1996-12-12", "250054906", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", johnPhoneNumbers, "bonesgmail.com", "Hamilton", "Maggy", "1234");
        //act - assert
        assertThrows(InvalidEmailException.class, () -> familyAndMemberService.startFamily(dto));
    }

    @Test
    @DisplayName("Unsuccessful method: invalid person email - already used")
    void startFamily_administratorIDAlreadyUsed() throws Exception, BusinessErrorMessage {
        //arrange
        //family bones
        FamilyAndAdminDTO bones = new FamilyAndAdminDTO("John Bones", "1996-12-12", "250054906", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", null, "bones@gmail.com", "Hamilton", "Bones", "1234");
        //family adams
        FamilyAndAdminDTO adams = new FamilyAndAdminDTO("John Adams", "1996-12-12", "277220777", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", null, "bones@gmail.com", "Hamilton", "Addy", "1234");
        //act & assert
        familyAndMemberService.startFamily(bones);
        assertThrows(PersonAlreadyInSystemException.class, () -> familyAndMemberService.startFamily(adams));
    }

    @Test
    @DisplayName("Add 2 families successfully")
    void startFamily_addTwoFamiliesSuccessfully() {
        //arrange
        //family bones
        FamilyAndAdminDTO bones = new FamilyAndAdminDTO("John Bones", "1996-12-12", "275001814", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", null, "bones@gmail.com", "Hamilton", "Bones", "1234");
        //family adams
        FamilyAndAdminDTO adams = new FamilyAndAdminDTO("John Adams", "1996-12-12", "277220777", "14", "Logical Street",
                "Porto", "Portugal", "2100-345", null, "adams@gmail.com", "Hamilton", "Addy", "1234");
        //act & assert
        assertDoesNotThrow(() -> familyAndMemberService.startFamily(bones));
        assertDoesNotThrow(() -> familyAndMemberService.startFamily(adams));
    }

    @Test
    @DisplayName("person's are not from the same family")
    void testAreFromTheSameHousehold() throws Exception {
        //arrange
        TelephoneNumberList numberList = new TelephoneNumberList();
        numberList.add(new TelephoneNumber("225658541"));
        Email indyID = new Email("indy@gmail.com");
        Email otherindyID = new Email("otherindy@gmail.com");
        Address address = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-01-22");
        PersonName indyName = new PersonName("Indiana JOnes");
        VAT vat = new VAT("123456789");
        FamilyID familyID1 = new FamilyID(UUID.randomUUID());
        FamilyID familyID2 = new FamilyID(UUID.randomUUID());
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();

        PersonVoDTO indyDTO = new PersonVoDTO(indyName, birthDate, vat, address, telephoneNumberList, indyID, familyID1, ledgerID);
        Person indy = PersonFactory.create(indyDTO);
        personRepository.save(indy);

        PersonVoDTO otherIndyDTO = new PersonVoDTO(indyName, birthDate, vat, address, telephoneNumberList, otherindyID, familyID2, ledgerID);
        Person otherIndy = PersonFactory.create(otherIndyDTO);
        personRepository.save(otherIndy);
        //act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            ReflectionTestUtils.invokeMethod(familyAndMemberService, "areFromTheSameHousehold", indyID, otherindyID, familyID2);
        });
    }
}
