package switchtwentytwenty.project.applicationservice.appservice.implappservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import switchtwentytwenty.project.autentication.ERole;
import switchtwentytwenty.project.autentication.RoleRepository;
import switchtwentytwenty.project.autentication.SignupDTO;
import switchtwentytwenty.project.autentication.UserRepository;
import switchtwentytwenty.project.domain.aggregate.person.Person;
import switchtwentytwenty.project.domain.aggregate.person.PersonFactory;
import switchtwentytwenty.project.domain.share.id.*;
import switchtwentytwenty.project.domain.share.list.EmailList;
import switchtwentytwenty.project.domain.share.persondata.*;
import switchtwentytwenty.project.domain.share.persondata.address.Address;
import switchtwentytwenty.project.dto.todomaindto.PersonJpaToDomainDTO;
import switchtwentytwenty.project.dto.todomaindto.PersonVoDTO;
import switchtwentytwenty.project.exception.BusinessErrorMessage;
import switchtwentytwenty.project.interfaceadaptor.repository.PersonRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AuthenticationServiceIT {

    @Autowired
    AuthorizationService authorizationService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PersonRepository personRepository;

    @BeforeEach
    void cleanRepository() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Create user successfully: one role")
    void registerUserSuccessfully_oneRole() throws BusinessErrorMessage {
        //arrange
        //in dto
        String username = "constantino";
        String email = "constantino@gmail.com";
        String password = "123456";
        String familyID = UUID.randomUUID().toString();
        Set<String> role = new HashSet<>();
        role.add("admin");
        SignupDTO signupDTO = new SignupDTO(username, email, password, familyID, role);
        //act
        authorizationService.registerUser(signupDTO);
        //assert
        assertTrue(userRepository.existsByUsername(username));
    }

    @Test
    @DisplayName("Create user successfully: two role")
    void registerUserSuccessfully_twoRole() throws BusinessErrorMessage {
        //arrange
        //in dto
        String username = "constantino";
        String email = "constantino@gmail.com";
        String password = "123456";
        String familyID = UUID.randomUUID().toString();
        Set<String> role = new HashSet<>();
        role.add("admin");
        role.add("user");
        SignupDTO signupDTO = new SignupDTO(username, email, password, familyID, role);
        //act
        authorizationService.registerUser(signupDTO);
        //assert
        assertTrue(userRepository.existsByUsername(username));
    }

    @Test
    @DisplayName("Create user successfully: none role")
    void registerUserSuccessfully_noneRole() throws BusinessErrorMessage {
        //arrange
        //in dto
        String username = "constantino";
        String email = "constantino@gmail.com";
        String password = "123456";
        String familyID = UUID.randomUUID().toString();
        SignupDTO signupDTO = new SignupDTO(username, email, password, familyID, null);
        //act
        authorizationService.registerUser(signupDTO);
        //assert
        assertTrue(userRepository.existsByUsername(username));
    }

    @Test
    @DisplayName("Create user unsuccessfully: username already used")
    void registerUserUnSuccessfully_usernameAlreadyExists() throws BusinessErrorMessage {
        //arrange
        //1st user
        String username = "constantino";
        String email = "constantino@gmail.com";
        String password = "123456";
        String familyID = UUID.randomUUID().toString();
        Set<String> role = new HashSet<>();
        role.add("admin");
        SignupDTO signupDTO = new SignupDTO(username, email, password, familyID, role);
        authorizationService.registerUser(signupDTO);
        //2st user
        String otherEmail = "constantino2@gmail.com";
        String otherPassword = "123456";
        SignupDTO otherSignupDTO = new SignupDTO(username, otherEmail, otherPassword, familyID, role);
        //act and assert
        assertThrows(BusinessErrorMessage.class, () -> {
            authorizationService.registerUser(otherSignupDTO);
        });
        //assert
        assertFalse(userRepository.existsByEmail(otherEmail));
        assertTrue(userRepository.existsByEmail(email));
    }

    @Test
    @DisplayName("Create user successfully: empty role")
    void registerUserUnSuccessfully_emptyRole() throws BusinessErrorMessage {
        //arrange
        //in dto
        String username = "constantino";
        String email = "constantino@gmail.com";
        String password = "123456";
        String familyID = UUID.randomUUID().toString();
        Set<String> role = new HashSet<>();
        SignupDTO signupDTO = new SignupDTO(username, email, password, familyID, role);
        //act
        authorizationService.registerUser(signupDTO);
        //assert
        assertTrue(userRepository.existsByUsername(username));

    }

    @Test
    @DisplayName("Create user unsuccessfully: email already used")
    void registerUserUnSuccessfully_emailAlreadyExists() throws BusinessErrorMessage {
        //arrange
        //1st user
        String username = "constantino";
        String email = "constantino@gmail.com";
        String password = "123456";
        String familyID = UUID.randomUUID().toString();
        Set<String> role = new HashSet<>();
        role.add("admin");
        SignupDTO signupDTO = new SignupDTO(username, email, password, familyID, role);
        authorizationService.registerUser(signupDTO);
        //2st user
        String otherUsername = "otherConstantino";
        String otherPassword = "123456";
        SignupDTO otherSignupDTO = new SignupDTO(otherUsername, email, otherPassword, familyID, role);
        //act and assert
        assertThrows(BusinessErrorMessage.class, () -> {
            authorizationService.registerUser(otherSignupDTO);
        });
        assertTrue(userRepository.existsByEmail(email));
    }

    /*@Test
    @DisplayName("Has access to account - Success")
    void hasAccessToAccountSuccess() throws Exception {

        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("220058942"));
        Email adminEmailSimpson = new Email("homer_simpson@gmail.com");
        Address address = new Address("Springfield", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1961-01-05");
        PersonName name = new PersonName("Homer Simpson");
        VAT vat = new VAT("245014314");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        EmailList emailList = new EmailList();
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accountIDList = new AccountIDList();
        accountIDList.add(accountID);

        PersonJpaToDomainDTO personJpaToDomainDTO = new PersonJpaToDomainDTO(name, birthDate, vat, address, telephoneNumberList, adminEmailSimpson, familyID, ledgerID, emailList, accountIDList);

        Person person = PersonFactory.create(personJpaToDomainDTO);
        personRepository.save(person);

        String username = "Homer";
        String password = "1234";
        String role = ERole.ROLE_SYSTEM_MANAGER.name();
        Set<String> roleList = new HashSet<>();
        roleList.add(role);

        SignupDTO signupDTO = new SignupDTO(username, adminEmailSimpson.toString(), password, familyID.toString(), roleList);
        authorizationService.registerUser(signupDTO);

        //act
        boolean result = authorizationService.accessAccountAuthorization(username, accountID.toString());

        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Has access to account - False")
    void hasAccessToAccountFalse() throws Exception {

        //arrange
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        TelephoneNumberList telephoneNumberList = new TelephoneNumberList();
        telephoneNumberList.add(new TelephoneNumber("220058942"));
        Email adminEmailSimpson = new Email("homer_simpson@gmail.com");
        Address address = new Address("Springfield", "25", "4125-886", "Porto", "Portugal");
        BirthDate birthDate = new BirthDate("1961-01-05");
        PersonName name = new PersonName("Homer Simpson");
        VAT vat = new VAT("245014314");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        EmailList emailList = new EmailList();
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountIDList accountIDList = new AccountIDList();
        accountIDList.add(accountID);

        PersonJpaToDomainDTO personJpaToDomainDTO = new PersonJpaToDomainDTO(name, birthDate, vat, address, telephoneNumberList, adminEmailSimpson, familyID, ledgerID, emailList, accountIDList);

        Person person = PersonFactory.create(personJpaToDomainDTO);
        personRepository.save(person);

        String username = "Homer";
        String password = "1234";
        String role = ERole.ROLE_SYSTEM_MANAGER.name();
        Set<String> roleList = new HashSet<>();
        roleList.add(role);

        SignupDTO signupDTO = new SignupDTO(username, adminEmailSimpson.toString(), password, familyID.toString(), roleList);
        authorizationService.registerUser(signupDTO);

        //act
        String otherAccountID = UUID.randomUUID().toString();
        boolean result = authorizationService.accessAccountAuthorization(username, otherAccountID);

        //assert
        assertFalse(result);
    }*/

  /*@Test
    @DisplayName("Create user successfully: one role")
    void authenticateUserSuccessfully() throws BusinessErrorMessage {
        //arrange
        //in dto
        String username = "constantino";
        String email = "constantino@gmail.com";
        String password = "123456";
        String familyID = UUID.randomUUID().toString();
        Set<String> role = new HashSet<>();
        role.add("admin");
        SignupDTO signupDTO = new SignupDTO(username, email, password, familyID, role);
        authorizationService.registerUser(signupDTO);
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO(username, password);
        //act
        authorizationService.authenticateUser(loginRequestDTO);
        //assert
        assertEquals(200, result.getStatusCodeValue());
    }

    @Test
    @DisplayName("Create user successfully: one role")
    void authenticateUserUnSuccessfully() {
        //arrange
        //in dto
        String username = "constantino";
        String password = "123456";
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO(username, password);
        //act & assert
        assertThrows(BadCredentialsException.class, () -> authorizationService.authenticateUser(loginRequestDTO));
    }*/
}