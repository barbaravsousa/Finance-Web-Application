package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import switchtwentytwenty.project.applicationservice.appservice.iappservice.ILedgerIDGenerator;
import switchtwentytwenty.project.applicationservice.irepository.ILedgerRepository;
import switchtwentytwenty.project.datamodel.FamilyJPA;
import switchtwentytwenty.project.domain.aggregate.family.Family;
import switchtwentytwenty.project.domain.aggregate.family.FamilyFactory;
import switchtwentytwenty.project.domain.aggregate.ledger.Ledger;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.domain.share.id.Email;
import switchtwentytwenty.project.domain.share.id.FamilyID;
import switchtwentytwenty.project.domain.share.id.LedgerID;
import switchtwentytwenty.project.domain.share.list.PersonList;
import switchtwentytwenty.project.domain.share.persondata.*;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.outdto.FamilyRelationOutDTO;
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;
import switchtwentytwenty.project.dto.toservicedto.PersonDTO;
import switchtwentytwenty.project.exception.BusinessErrorMessage;
import switchtwentytwenty.project.exception.ElementNotFoundException;
import switchtwentytwenty.project.exception.InvalidVATException;
import switchtwentytwenty.project.exception.PersonAlreadyInSystemException;
import switchtwentytwenty.project.interfaceadaptor.repository.FamilyRepository;
import switchtwentytwenty.project.interfaceadaptor.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FamilyAndMemberServiceTest {

    @Spy
    @InjectMocks
    FamilyAndMemberService familyAndMemberService;
    @Mock
    FamilyRepository familyRepository;
    @Mock
    PersonRepository personRepository;
    @Mock
    ILedgerRepository ledgerRepository;
    @Mock
    ILedgerIDGenerator ledgerIDGenerator;
    @Mock
    AuthorizationService authorizationService;

    @Test
    @DisplayName("Add family member successfully")
    void addFamilyMemberSuccessfully() throws BusinessErrorMessage {
        //arrange
        List<String> johnPhoneNumbers = new ArrayList<>();
        johnPhoneNumbers.add("912343546");
        PersonDTO personDTO = new PersonDTO.PersonDTOBuilder()
                .withName("Ricardo Constantino")
                .withBirthDate("1999-12-10")
                .withCity("Porto")
                .withHouseNumber("1ºEsq")
                .withCountry("Portugal")
                .withStreet("Rua da Boa Vida")
                .withPhoneNumbers(johnPhoneNumbers)
                .withZipCode("9852-634")
                .withVat("123456789")
                .withEmail("oli@gmail.com")
                .withFamilyID(UUID.randomUUID().toString())
                .withUsername("Ricky")
                .withPassword("1234")
                .build();
        when(familyRepository.existsByID(Mockito.any(FamilyID.class))).thenReturn(true);
        when(personRepository.existsByID(Mockito.any(Email.class))).thenReturn(false);
        when(personRepository.existsByFamilyIDAndVat(Mockito.any(FamilyID.class), Mockito.any(VAT.class))).thenReturn(false);
        when(ledgerIDGenerator.generate()).thenReturn(new LedgerID(UUID.randomUUID()));
        doNothing().when(personRepository).save(Mockito.any(Person.class));
        doNothing().when(ledgerRepository).save(Mockito.any(Ledger.class));
        doNothing().when(authorizationService).registerUser(any());
        //act - assert
        assertDoesNotThrow(() -> familyAndMemberService.addFamilyMember(personDTO));
    }

    @Test
    @DisplayName("Failure add family member: family not exists")
    void failureAddFamilyMemberSuccessfully_FamilyNotExists() {
        //arrange
        List<String> johnPhoneNumbers = new ArrayList<>();
        johnPhoneNumbers.add("912343546");
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
                .withEmail("oli@gmail.com")
                .withFamilyID(UUID.randomUUID().toString())
                .build();
        when(familyRepository.existsByID(Mockito.any(FamilyID.class))).thenReturn(false);
        //act - assert
        assertThrows(ElementNotFoundException.class, () -> familyAndMemberService.addFamilyMember(personDTO));
    }

    @Test
    @DisplayName("Failure add family member: person already exists")
    void failureAddFamilyMemberSuccessfully_PersonAlreadyExists() {
        //arrange
        List<String> johnPhoneNumbers = new ArrayList<>();
        johnPhoneNumbers.add("912343546");
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
                .withEmail("oli@gmail.com")
                .withFamilyID(UUID.randomUUID().toString())
                .build();
        when(familyRepository.existsByID(Mockito.any(FamilyID.class))).thenReturn(true);
        when(personRepository.existsByID(Mockito.any(Email.class))).thenReturn(true);
        //act - assert
        assertThrows(PersonAlreadyInSystemException.class, () -> familyAndMemberService.addFamilyMember(personDTO));
    }

    @Test
    @DisplayName("Failure add family member: vat already exists in family")
    void failureAddFamilyMemberSuccessfully_VATAlreadyExistsInFamily() throws Exception {
        //arrange
        UUID familyID = UUID.randomUUID();
        List<String> johnPhoneNumbers = new ArrayList<>();
        johnPhoneNumbers.add("912343546");
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
                .withEmail("oli@gmail.com")
                .withFamilyID(UUID.randomUUID().toString())
                .build();

        List<String> telephones = new ArrayList<>();
        telephones.add("922658453");
        PersonVoDTO voPersonDTO = new PersonVoDTO(
                new PersonName("Joaquina"),
                new BirthDate("2020-03-02"),
                new VAT("123456789"),
                new Address("Rua Escura", "25", "2156-956", "Porto", "Portugal"),
                new TelephoneNumberList(telephones),
                new Email("email@gmail.com"),
                new FamilyID(familyID),
                new LedgerID(UUID.randomUUID()));
        Person member = PersonFactory.create(voPersonDTO);

        PersonList personList = new PersonList();
        personList.add(member);

        when(familyRepository.existsByID(Mockito.any(FamilyID.class))).thenReturn(true);
        when(personRepository.existsByID(Mockito.any(Email.class))).thenReturn(false);
        when(personRepository.existsByFamilyIDAndVat(Mockito.any(FamilyID.class), Mockito.any(VAT.class))).thenReturn(true);
        //act - assert
        assertThrows(InvalidVATException.class, () -> familyAndMemberService.addFamilyMember(personDTO));
    }

    @Test
    @DisplayName("Are not from the same household")
    void areNotFromTheSameHousehold() throws Exception {
        //arrange
        boolean result;

        //Person One, aka Alan Turing
        FamilyID familyIDOne = new FamilyID(UUID.randomUUID());
        TelephoneNumber telephonesOne = new TelephoneNumber("225658541");
        Address address = new Address("Rua Velha", "13", "4100-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-01-15");
        Email idOne = new Email("alan_turing@gmail.com");
        PersonName name = new PersonName("Alan Turing");
        VAT vat = new VAT("234938455");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(telephonesOne);
        PersonVoDTO oneDTO = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, idOne, familyIDOne, new LedgerID(UUID.randomUUID()));
        Person personOne = PersonFactory.create(oneDTO);

        //Person Two, aka Margaret Hamilton
        FamilyID familyIDTwo = new FamilyID(UUID.randomUUID());
        TelephoneNumber telephonesTwo = new TelephoneNumber("910101000");
        Address addressTwo = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDateTwo = new BirthDate("1905-03-05");
        Email idTwo = new Email("margaret_hamilton@gmail.com");
        PersonName nameTwo = new PersonName("Margaret Hamilton");
        VAT vatTwo = new VAT("123456789");
        TelephoneNumberList telephoneNumberListTwo = new TelephoneNumberList();
        telephoneNumberList.add(telephonesTwo);
        PersonVoDTO twoDTO = new PersonVoDTO(nameTwo, birthDateTwo, vatTwo, addressTwo, telephoneNumberListTwo, idTwo, familyIDTwo, new LedgerID(UUID.randomUUID()));
        Person personTwo = PersonFactory.create(twoDTO);

        //arrange mockito
        Mockito.doReturn(personOne).when(personRepository).findByID(idOne);
        Mockito.doReturn(personTwo).when(personRepository).findByID(idTwo);

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> {
            familyAndMemberService.areFromTheSameHousehold(idOne, idTwo, familyIDOne);
        });
    }

    @Test
    @DisplayName("Are from the same household")
    void areFromTheSameHousehold() throws Exception {
        //arrange
        boolean result;

        //Family
        FamilyID familyID = new FamilyID(UUID.randomUUID());

        //Person One, aka Alan Turing
        TelephoneNumber telephonesOne = new TelephoneNumber("225658541");
        Address address = new Address("Rua Velha", "13", "4100-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1995-01-02");
        Email idOne = new Email("alan_turing@gmail.com");
        PersonName name = new PersonName("Alan Turing");
        VAT vat = new VAT("234938455");
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(telephonesOne);
        PersonVoDTO oneDTO = new PersonVoDTO(name, birthDate, vat, address, telephoneNumberList, idOne, familyID, new LedgerID(UUID.randomUUID()));
        Person personOne = PersonFactory.create(oneDTO);

        //Person Two, aka Margaret Hamilton
        TelephoneNumber telephonesTwo = new TelephoneNumber("910101000");
        Address addressTwo = new Address("Rua Nova", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDateTwo = new BirthDate("1905-03-05");
        Email idTwo = new Email("margaret_hamilton@gmail.com");
        PersonName nameTwo = new PersonName("Margaret Hamilton");
        VAT vatTwo = new VAT("123456789");
        TelephoneNumberList telephoneNumberListTwo = new TelephoneNumberList();
        telephoneNumberList.add(telephonesTwo);
        PersonVoDTO twoDTO = new PersonVoDTO(nameTwo, birthDateTwo, vatTwo, addressTwo, telephoneNumberListTwo, idTwo, familyID, new LedgerID(UUID.randomUUID()));
        Person personTwo = PersonFactory.create(twoDTO);

        //arrange mockito
        Mockito.doReturn(personOne).when(personRepository).findByID(idOne);
        Mockito.doReturn(personTwo).when(personRepository).findByID(idTwo);

        //act
        result = familyAndMemberService.areFromTheSameHousehold(idOne, idTwo, familyID);

        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Are from same household - personID is null")
    void areFromSameHouseholdPersonIDNull() throws Exception {
        //arrange
        Email personID = null;
        Email kinID = new Email("john_von_neumann@hotmail.com");
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        //act and assert
        assertThrows(IllegalArgumentException.class, () -> familyAndMemberService.areFromTheSameHousehold(personID, kinID, familyID));
    }

    @Test
    @DisplayName("Are from same household - kinID is null")
    void areFromSameHouseholdKinIDNull() throws Exception {
        //arrange
        Email personID = new Email("john_von_neumann@hotmail.com");
        Email kinID = null;
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        //act and assert
        assertThrows(IllegalArgumentException.class, () -> familyAndMemberService.areFromTheSameHousehold(personID, kinID, familyID));
    }

    @Test
    @DisplayName("Are from same household - familyID is null")
    void areFromSameHouseholdFamilyIDNull() throws Exception {
        //arrange
        Email personID = new Email("john_von_neumann@hotmail.com");
        Email kinID = new Email("margaret_hamilton@hotmail.com");
        FamilyID familyID = null;
        //act and assert
        assertThrows(IllegalArgumentException.class, () -> familyAndMemberService.areFromTheSameHousehold(personID, kinID, familyID));
    }

    @Test
    @DisplayName("Are from the same household : same persons")
    void areFromTheSameHouseholdSamePersons() throws Exception {
        //arrange
        //family
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Email personID = new Email("john_von_neumann@hotmail.com");

        //act and assert
        assertThrows(IllegalArgumentException.class, () -> familyAndMemberService.areFromTheSameHousehold(personID, personID, familyID));
    }

    @Test
    @DisplayName("Create a family relation")
    void createFamilyRelation() throws Exception {
        //arrange
        Optional<FamilyRelationOutDTO> optional;
        String kinID = "magaret_hamilton@gmail.com";
        String relationType = Constants.PARENT;

        FamilyID familyID = new FamilyID(UUID.randomUUID());
        FamilyName familyName = new FamilyName("Turing");
        Email adminEmail = new Email("alan_turing@gmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, ledgerID, adminEmail, familyName);
        Family family = FamilyFactory.create(familyDTO);

        FamilyRelationOutDTO outDTO = new FamilyRelationOutDTO(adminEmail.toString(), kinID, relationType);

        //arrange mock
        doReturn(family).when(familyRepository).findByID(familyID);
        doReturn(new FamilyJPA()).when(familyRepository).save(any());
        doReturn(true).when(familyAndMemberService).areFromTheSameHousehold(any(), any(), any());
        doReturn(Optional.of(outDTO)).when(familyAndMemberService).familyRepositorySaveResponseHandler(any(), any(), any());

        //act
        optional = familyAndMemberService.createFamilyRelation(adminEmail.toString(), kinID, familyID.toString(), relationType);

        //assert
        assertTrue(optional.isPresent());

        String personIDResult = optional.get().getPersonID();
        String kinIDResult = optional.get().getKinID();
        String relationTypeResult = optional.get().getRelationType();

        assertEquals(personIDResult, adminEmail.toString());
        assertEquals(kinIDResult, kinID);
        assertEquals(relationTypeResult, relationType);
    }

    @Test
    @DisplayName("Create a family relation - not from the same household")
    void createFamilyRelationNotFromTheSameHousehold() throws Exception {
        //arrange
        Optional<FamilyRelationOutDTO> optional;
        String kinID = "magaret_hamilton@gmail.com";
        String relationType = Constants.PARENT;

        FamilyID familyID = new FamilyID(UUID.randomUUID());
        Email adminEmail = new Email("alan_turing@gmail.com");

        //arrange mock
        doReturn(false).when(familyAndMemberService).areFromTheSameHousehold(any(), any(), any());
        //act
        optional = familyAndMemberService.createFamilyRelation(adminEmail.toString(), kinID, familyID.toString(), relationType);
        //assert
        assertFalse(optional.isPresent());
    }
}

