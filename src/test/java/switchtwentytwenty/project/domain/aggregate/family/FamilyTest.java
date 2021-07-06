package switchtwentytwenty.project.domain.aggregate.family;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import switchtwentytwenty.project.domain.constant.Constants;
import switchtwentytwenty.project.domain.share.familydata.FamilyRelation;
import switchtwentytwenty.project.domain.share.familydata.RegistrationDate;
import switchtwentytwenty.project.domain.share.familydata.RelationType;
import switchtwentytwenty.project.domain.share.id.*;
import switchtwentytwenty.project.domain.share.familydata.FamilyName;
import switchtwentytwenty.project.dto.todomaindto.FamilyVoDTO;
import switchtwentytwenty.project.exception.InvalidEmailException;
import switchtwentytwenty.project.exception.InvalidRelationTypeException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FamilyTest {

    @Test
    @DisplayName("Invalid Family: null family id")
    void invalidFamilyNullFamilyID() throws Exception {
        //arrange
        FamilyID familyID = null;
        FamilyName familyName = new FamilyName("Turing");
        Email adminEmail = new Email("alan_turing@hotmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, ledgerID, adminEmail, familyName);
        //act and assert
        assertThrows(NullPointerException.class, () -> FamilyFactory.create(familyDTO));
    }

    @Test
    @DisplayName("Invalid Family: null family name")
    void invalidFamilyNullFamilyName() throws Exception {
        //arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        FamilyName familyName = null;
        Email adminEmail = new Email("alan_turing@hotmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, ledgerID, adminEmail, familyName);
        //act and assert
        assertThrows(NullPointerException.class, () -> FamilyFactory.create(familyDTO));
    }

    @Test
    @DisplayName("Invalid Family: null administrator id")
    void invalidFamilyNullAdministratorID() {
        //arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        FamilyName familyName = new FamilyName("Turing");
        Email adminEmail = null;
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, ledgerID, adminEmail, familyName);
        //act and assert
        assertThrows(NullPointerException.class, () -> FamilyFactory.create(familyDTO));
    }


    @Test
    @DisplayName("Get Family id")
    void getFamilyID() throws InvalidEmailException {
        //arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        FamilyName familyName = new FamilyName("Turing");
        Email adminEmail = new Email("alan_turing@hotmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, ledgerID, adminEmail, familyName);
        Family family = FamilyFactory.create(familyDTO);
        //act
        ID result = family.getFamilyID();
        ID result1= family.getID();
        // assert
        assertEquals(result, familyID);
        assertEquals(result1, familyID);
    }

    @Test
    @DisplayName("hasCashAccount and owns cash account")
    void hasCashAccount() throws InvalidEmailException {
        //arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        FamilyName familyName = new FamilyName("Turing");
        Email adminEmail = new Email("alan_turing@hotmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, ledgerID, adminEmail, familyName);
        Family family = FamilyFactory.create(familyDTO);
        AccountID accountID= new AccountID(UUID.randomUUID());
        family.addAccountID(accountID);
        //act
        boolean resultHasCashAccount = family.hasCashAccount();
        boolean resultOwnsCashAccount=family.ownsCashAccount(accountID);
        // assert
        assertTrue(resultHasCashAccount);
        assertTrue(resultOwnsCashAccount);
    }
    @Test
    @DisplayName("Does not own cash Account")
    void doesNotOwnCashAccount() throws InvalidEmailException {
        //arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        FamilyName familyName = new FamilyName("Turing");
        Email adminEmail = new Email("alan_turing@hotmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, ledgerID, adminEmail, familyName);
        Family family = FamilyFactory.create(familyDTO);
        AccountID accountID= new AccountID(UUID.randomUUID());
        family.addAccountID(accountID);
        AccountID anotherAccountID= new AccountID(UUID.randomUUID());
        //act
        boolean resultOwnsCashAccount=family.ownsCashAccount(anotherAccountID);
        // assert
        assertFalse(resultOwnsCashAccount);
    }


    @Test
    @DisplayName("create Family relation")
    void createFamilyRelation() throws InvalidEmailException, IOException, InvalidRelationTypeException {
        //arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        FamilyName familyName = new FamilyName("Turing");
        Email adminEmail = new Email("alan_turing@hotmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, ledgerID, adminEmail, familyName);
        Family family = FamilyFactory.create(familyDTO);
        Email personId = new Email("constantino@hotmail.com");
        Email kinId= new Email("maria@hotmail.com");
        RelationType relation = RelationType.getInstance(Constants.CHILD);
        //act
        FamilyRelation resultingFamilyRelation= family.createFamilyRelation(personId,kinId,relation);

        List<FamilyRelation> relationList=family.getFamilyRelationList();
        relationList.add(resultingFamilyRelation);
        FamilyRelation expected =relationList.get(1);
        // assert
        assertEquals(expected,resultingFamilyRelation);
    }



    @Test
    @DisplayName("Get list of family relations from a person")
    void getListOfFamilyRelations() throws Exception {
        //arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        FamilyName familyNameMcFly = new FamilyName("McFly");

        Email adminEmailMcFly = new Email("marty_mcfly@gmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, ledgerID, adminEmailMcFly, familyNameMcFly);
        Family familyMcFly = FamilyFactory.create(familyDTO);

        Email otherMemberGeorge = new Email("george_mcfly@sapo.pt");
        Email otherMemberLoraine = new Email("loraine_mcfly@sapo.pt");

        RelationType relationMartyGeorge = RelationType.getInstance(Constants.CHILD);
        RelationType relationMartyLoraine = RelationType.getInstance(Constants.CHILD);
        RelationType relationGeorgeLoraine = RelationType.getInstance(Constants.CHILD);

        familyMcFly.createFamilyRelation(adminEmailMcFly, otherMemberGeorge, relationMartyGeorge);
        familyMcFly.createFamilyRelation(otherMemberLoraine, adminEmailMcFly, relationMartyLoraine);
        familyMcFly.createFamilyRelation(otherMemberGeorge, otherMemberLoraine, relationGeorgeLoraine);

        //act
        int result = familyMcFly.getFamilyRelationByPersonID(adminEmailMcFly).size();
        int expected = 2;

        // assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Get list of family relations from a person - Mock")
    void getListOfFamilyRelations2() throws Exception {
        //arrange

        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        FamilyName familyNameMcFly = new FamilyName("McFly");

        Email adminEmailMcFly = new Email("marty_mcfly@gmail.com");

        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, ledgerID, adminEmailMcFly, familyNameMcFly);
        Family familyMcFly = FamilyFactory.create(familyDTO);

        Email otherMemberGeorge = new Email("george_mcfly@sapo.pt");
        Email otherMemberLoraine = new Email("loraine_mcfly@sapo.pt");

        RelationType relationGeorgeLoraine = RelationType.getInstance(Constants.SPOUSE);

        familyMcFly.createFamilyRelation(otherMemberLoraine, otherMemberGeorge, relationGeorgeLoraine);

        //act
        int result = familyMcFly.getFamilyRelationByPersonID(adminEmailMcFly).size();
        int expected = 0;

        // assert
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Family has same ID")
    void familyHasSameID() throws Exception {
        //arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        FamilyName familyNameTuring = new FamilyName("Turing");
        FamilyName familyNameNeumann = new FamilyName("Neumann");
        Email adminEmailTuring = new Email("alan_turing@hotmail.com");
        Email adminEmailNeumann = new Email("john_neumann@hotmail.com");
        LedgerID ledgerID1 = new LedgerID(UUID.randomUUID());
        LedgerID ledgerID2 = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO1 = new FamilyVoDTO(familyID, ledgerID1, adminEmailTuring, familyNameTuring);
        Family familyTuring = FamilyFactory.create(familyDTO1);
        FamilyVoDTO familyDTO2 = new FamilyVoDTO(familyID, ledgerID2, adminEmailNeumann, familyNameNeumann);
        Family familyNeumann = FamilyFactory.create(familyDTO2);
        //act
        ID familyIDTuring = familyTuring.getID();
        ID familyIDNeumann = familyNeumann.getID();
        //assert
        assertEquals(familyIDNeumann, familyIDTuring);
    }

    @Test
    @DisplayName("Family has same identity")
    void familyHasSameIdentity() throws Exception {
        //arrange
        boolean result;
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        FamilyName familyNameTuring = new FamilyName("Turing");
        FamilyName familyNameNeumann = new FamilyName("Neumann");
        Email adminEmailTuring = new Email("alan_turing@hotmail.com");
        Email adminEmailNeumann = new Email("john_neumann@hotmail.com");
        LedgerID ledgerID1 = new LedgerID(UUID.randomUUID());
        LedgerID ledgerID2 = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO1 = new FamilyVoDTO(familyID, ledgerID1, adminEmailTuring, familyNameTuring);
        Family familyTuring = FamilyFactory.create(familyDTO1);
        FamilyVoDTO familyDTO2 = new FamilyVoDTO(familyID, ledgerID2, adminEmailNeumann, familyNameNeumann);
        Family familyNeumann = FamilyFactory.create(familyDTO2);
        //act
        result = familyNeumann.equals(familyTuring);
        //assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Family has not same identity")
    void familyHasNotSameIdentity() throws Exception {
        //arrange
        boolean result;
        UUID idTuring = UUID.randomUUID();
        UUID idNeumann = UUID.randomUUID();
        FamilyID familyIDTuring = new FamilyID(idTuring);
        FamilyID familyIDNeumann = new FamilyID(idNeumann);
        FamilyName familyNameTuring = new FamilyName("Turing");
        FamilyName familyNameNeumann = new FamilyName("Neumann");
        Email adminEmailTuring = new Email("alan_turing@hotmail.com");
        Email adminEmailNeumann = new Email("john_neumann@hotmail.com");
        LedgerID ledgerID1 = new LedgerID(UUID.randomUUID());
        LedgerID ledgerID2 = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO1 = new FamilyVoDTO(familyIDTuring, ledgerID1, adminEmailTuring, familyNameTuring);
        Family familyTuring = FamilyFactory.create(familyDTO1);
        FamilyVoDTO familyDTO2 = new FamilyVoDTO(familyIDNeumann, ledgerID2, adminEmailNeumann, familyNameNeumann);
        Family familyNeumann = FamilyFactory.create(familyDTO2);
        //act
        result = familyNeumann.equals(familyTuring);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Same hashCode")
    void sameHashCode() throws Exception {
        //arrange
        int hashCode1;
        int hashCode2;
        UUID id = UUID.randomUUID();
        FamilyID familyIDTuring = new FamilyID(id);
        FamilyID familyIDNeumann = new FamilyID(id);
        FamilyName familyNameTuring = new FamilyName("Turing");
        FamilyName familyNameNeumann = new FamilyName("Turing");
        Email adminEmailTuring = new Email("alan_turing@hotmail.com");
        Email adminEmailNeumann = new Email("alan_turing@hotmail.com");
        LedgerID ledgerID1 = new LedgerID(UUID.randomUUID());
        LedgerID ledgerID2 = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO1 = new FamilyVoDTO(familyIDTuring, ledgerID1, adminEmailTuring, familyNameTuring);
        Family familyTuring = FamilyFactory.create(familyDTO1);
        FamilyVoDTO familyDTO2 = new FamilyVoDTO(familyIDNeumann, ledgerID2, adminEmailNeumann, familyNameNeumann);
        Family familyNeumann = FamilyFactory.create(familyDTO2);
        //act
        hashCode1 = familyNeumann.hashCode();
        hashCode2 = familyTuring.hashCode();
        //assert
        assertEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Not same hashCode")
    void notSameHashCode() throws Exception {
        //arrange
        int hashCode1;
        int hashCode2;
        UUID idTuring = UUID.randomUUID();
        UUID idNeumann = UUID.randomUUID();
        FamilyID familyIDTuring = new FamilyID(idTuring);
        FamilyID familyIDNeumann = new FamilyID(idNeumann);
        FamilyName familyNameTuring = new FamilyName("Turing");
        FamilyName familyNameNeumann = new FamilyName("Neumann");
        Email adminEmailTuring = new Email("alan_turing@hotmail.com");
        Email adminEmailNeumann = new Email("john_neumann@hotmail.com");
        LedgerID ledgerID1 = new LedgerID(UUID.randomUUID());
        LedgerID ledgerID2 = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO1 = new FamilyVoDTO(familyIDTuring, ledgerID1, adminEmailTuring, familyNameTuring);
        Family familyTuring = FamilyFactory.create(familyDTO1);
        FamilyVoDTO familyDTO2 = new FamilyVoDTO(familyIDNeumann, ledgerID2, adminEmailNeumann, familyNameNeumann);
        Family familyNeumann = FamilyFactory.create(familyDTO2);
        //act
        hashCode1 = familyNeumann.hashCode();
        hashCode2 = familyTuring.hashCode();
        //assert
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    @DisplayName("Compare same object")
    void compareSameObject() throws Exception {
        //arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        FamilyName familyName = new FamilyName("Turing");
        Email adminEmail = new Email("alan_turing@hotmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO1 = new FamilyVoDTO(familyID, ledgerID, adminEmail, familyName);
        //act
        Family family1 = FamilyFactory.create(familyDTO1);
        Family family2 = family1;
        // assert
        assertEquals(family1, family2);
    }

    @Test
    @DisplayName("Compare with null")
    void compareWithNull() throws Exception {
        //arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        FamilyName familyName = new FamilyName("Turing");
        Email adminEmail = new Email("alan_turing@hotmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        //act
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, ledgerID, adminEmail, familyName);
        Family family1 = FamilyFactory.create(familyDTO);
        Family family2 = null;
        // assert
        assertNotEquals(family1, family2);
    }

    @Test
    @DisplayName("Compare with different instance")
    void compareWithDifferentInstance() throws Exception {
        //arrange
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        FamilyName familyName = new FamilyName("Turing");
        Email adminEmail = new Email("alan_turing@hotmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        //act
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, ledgerID, adminEmail, familyName);
        Family family = FamilyFactory.create(familyDTO);
        // assert
        assertNotEquals(family, familyName);
    }

    @Test
    @DisplayName("Update family relation")
    void updateFamilyRelation() throws Exception {
        //arrange
        int result;
        int expected = 1;
        UUID id = UUID.randomUUID();
        FamilyID familyID = new FamilyID(id);
        FamilyName familyName = new FamilyName("Turing");
        Email adminEmail = new Email("alan_turing@hotmail.com");
        Email familyMember = new Email("john_von_neumann@gmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, ledgerID, adminEmail, familyName);
        Family family = FamilyFactory.create(familyDTO);

        RelationType parent = RelationType.getInstance(Constants.PARENT);
        RelationType uncle = RelationType.getInstance(Constants.UNCLE);

        family.createFamilyRelation(adminEmail, familyMember, parent);
        family.createFamilyRelation(familyMember, adminEmail, uncle);

        //act
        result = family.getFamilyRelationByPersonID(adminEmail).size();

        //assert
        assertEquals(result, expected);
    }

    @Test
    void addAccountID() throws Exception {
        //arrange
        String familyAdminID = "albertina@gmail.com";
        UUID familyID = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(new FamilyID(familyID), ledgerID, new Email(familyAdminID), new FamilyName("Jones"));
        Family family = FamilyFactory.create(familyDTO);
        AccountID accountID = new AccountID(UUID.randomUUID());
        //act
        boolean result = family.addAccountID(accountID);
        //assert
        assertTrue(result);
    }

    @Test
    void addTwoAccountIDs() throws Exception {
        //arrange
        String familyAdminID = "albertina@gmail.com";
        UUID familyID = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(new FamilyID(familyID), ledgerID, new Email(familyAdminID), new FamilyName("Jones"));
        Family family = FamilyFactory.create(familyDTO);
        AccountID accountID = new AccountID(UUID.randomUUID());
        AccountID accountID1 = new AccountID(UUID.randomUUID());
        //act
        family.addAccountID(accountID);
        boolean result = family.addAccountID(accountID1);
        //assert
        assertFalse(result);
    }


    @Test
    void isAdministrator() throws Exception {
        //arrange
        String familyAdminID = "albertina@gmail.com";
        Email admin = new Email(familyAdminID);
        UUID familyID = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(new FamilyID(familyID), ledgerID, new Email(familyAdminID), new FamilyName("Jones"));
        Family family = FamilyFactory.create(familyDTO);
        //act
        boolean result = family.isAdministrator(admin);
        //assert
        assertTrue(result);
    }

    @Test
    void isNotTheAdministrator() throws Exception {
        //arrange
        String familyAdminID = "albertina@gmail.com";
        String notAdmin = "notAdmin@gmail.com";
        Email admin = new Email(familyAdminID);
        Email person = new Email(notAdmin);
        UUID familyID = UUID.randomUUID();
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(new FamilyID(familyID), ledgerID, admin, new FamilyName("Jones"));
        Family family = FamilyFactory.create(familyDTO);
        //act
        boolean result = family.isAdministrator(person);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("not same value as other family: differentID")
    void notSameAs_DifferentID() throws Exception {
        //arrange
        String familyAdminID = "albertina@gmail.com";
        Email admin = new Email(familyAdminID);
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        FamilyID otherFamilyID = new FamilyID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, ledgerID, admin, new FamilyName("Jones"));
        Family family = FamilyFactory.create(familyDTO);
        FamilyVoDTO otherFamilyDTO = new FamilyVoDTO(otherFamilyID, ledgerID, admin, new FamilyName("Jones"));
        Family otherFamily = FamilyFactory.create(otherFamilyDTO);
        //act
        boolean result = family.sameValueAs(otherFamily);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("not same value as other family: differentName")
    void notSameAs_DifferentName() throws Exception {
        //arrange
        String familyAdminID = "albertina@gmail.com";
        Email admin = new Email(familyAdminID);
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, ledgerID, admin, new FamilyName("Jones"));
        Family family = FamilyFactory.create(familyDTO);
        FamilyVoDTO otherFamilyDTO = new FamilyVoDTO(familyID, ledgerID, admin, new FamilyName("Adam"));
        Family otherFamily = FamilyFactory.create(otherFamilyDTO);
        //act
        boolean result = family.sameValueAs(otherFamily);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("not same value as other family: different admin")
    void notSameAs_DifferentAdministratorID() throws Exception {
        //arrange
        String familyAdminID = "albertina@gmail.com";
        String otherFamilyAdminID = "alberto@gmail.com";
        Email admin = new Email(familyAdminID);
        Email otherAdmin = new Email(otherFamilyAdminID);
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, ledgerID, admin, new FamilyName("Jones"));
        Family family = FamilyFactory.create(familyDTO);
        FamilyVoDTO otherFamilyDTO = new FamilyVoDTO(familyID, ledgerID, otherAdmin, new FamilyName("Adam"));
        Family otherFamily = FamilyFactory.create(otherFamilyDTO);
        //act
        boolean result = family.sameValueAs(otherFamily);
        //assert
        assertFalse(result);
    }

    @Test
    void hasSameID() throws Exception {
        //arrange
        String familyAdminID = "albertina@gmail.com";
        Email admin = new Email(familyAdminID);
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, ledgerID, admin, new FamilyName("Jones"));
        Family family = FamilyFactory.create(familyDTO);
        //act
        boolean result = family.hasSameID(familyID);
        //assert
        assertTrue(result);
    }

    @Test
    void hasNotSameID() throws Exception {

        String familyAdminID = "albertina@gmail.com";
        Email admin = new Email(familyAdminID);
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        FamilyID familyID = new FamilyID(UUID.randomUUID());
        FamilyVoDTO familyDTO = new FamilyVoDTO(familyID, ledgerID, admin, new FamilyName("Jones"));
        Family family = FamilyFactory.create(familyDTO);
        //act
        boolean result = family.hasSameID(null);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Family has the same value - True")
    void sameValueTrue() throws Exception {
        //arrange
        boolean result;
        UUID idTuring = UUID.randomUUID();
        FamilyID familyIDTuring = new FamilyID(idTuring);
        FamilyName familyNameTuring = new FamilyName("Turing");
        Email adminEmailTuring = new Email("alan_turing@hotmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("08/01/1970");
        RegistrationDate registrationDate = new RegistrationDate();
        registrationDate.setDate(date);
        FamilyVoDTO familyDTO1 = new FamilyVoDTO(familyIDTuring, ledgerID, adminEmailTuring, familyNameTuring);
        Family familyTuring = FamilyFactory.create(familyDTO1);
        FamilyVoDTO familyDTO2 = new FamilyVoDTO(familyIDTuring, ledgerID, adminEmailTuring, familyNameTuring);
        Family familyNeumann = FamilyFactory.create(familyDTO2);
        familyNeumann.setRegistrationDate(registrationDate);
        familyTuring.setRegistrationDate(registrationDate);
        //act
        result = familyNeumann.sameValueAs(familyTuring);
        //assert
        assertTrue(result);
    }


    @Test
    @DisplayName("Family has the same value - False")
    void sameValueFalse() throws InvalidEmailException {
        //arrange
        boolean result;
        UUID idTuring = UUID.randomUUID();
        UUID idNeumann = UUID.randomUUID();
        FamilyID familyIDTuring = new FamilyID(idTuring);
        FamilyID familyIDNeumann = new FamilyID(idNeumann);
        FamilyName familyNameTuring = new FamilyName("Turing");
        FamilyName familyNameNeumann = new FamilyName("Neumann");
        Email adminEmailTuring = new Email("alan_turing@hotmail.com");
        Email adminEmailNeumann = new Email("john_neumann@hotmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());

        FamilyVoDTO familyDTO1 = new FamilyVoDTO(familyIDTuring, ledgerID, adminEmailTuring, familyNameTuring);
        Family familyTuring = FamilyFactory.create(familyDTO1);
        FamilyVoDTO familyDTO2 = new FamilyVoDTO(familyIDNeumann, ledgerID, adminEmailNeumann, familyNameNeumann);
        Family familyNeumann = FamilyFactory.create(familyDTO2);
        //act
        result = familyNeumann.sameValueAs(familyTuring);
        //assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Family has the same value - False")
    void sameValue_False_differentRegistrationDate() throws InvalidEmailException, ParseException {
        //arrange
        boolean result;
        UUID idTuring = UUID.randomUUID();
        FamilyID familyIDTuring = new FamilyID(idTuring);
        FamilyName familyNameTuring = new FamilyName("Turing");
        Email adminEmailTuring = new Email("alan_turing@hotmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("08/01/1970");
        RegistrationDate registrationDate = new RegistrationDate();
        registrationDate.setDate(date);
        RegistrationDate registrationDate2 = new RegistrationDate();
        FamilyVoDTO familyDTO1 = new FamilyVoDTO(familyIDTuring, ledgerID, adminEmailTuring, familyNameTuring);
        Family familyTuring = FamilyFactory.create(familyDTO1);
        FamilyVoDTO familyDTO2 = new FamilyVoDTO(familyIDTuring, ledgerID, adminEmailTuring, familyNameTuring);
        Family familyNeumann = FamilyFactory.create(familyDTO2);
        familyNeumann.setRegistrationDate(registrationDate);
        familyTuring.setRegistrationDate(registrationDate2);
        //act
        result = familyNeumann.sameValueAs(familyTuring);
        //assert
        assertFalse(result);
    }
    @Test
    @DisplayName("Family has the same value - False")
    void sameValue_False_differentAdmnistratorID() throws InvalidEmailException, ParseException {
        //arrange
        boolean result;
        UUID idTuring = UUID.randomUUID();
        FamilyID familyIDTuring = new FamilyID(idTuring);
        FamilyName familyNameTuring = new FamilyName("Turing");
        Email adminEmailTuring = new Email("alan_turing@hotmail.com");
        Email adminEmailNeumann = new Email("josh_neumann@hotmail.com");
        LedgerID ledgerID = new LedgerID(UUID.randomUUID());
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("08/01/1970");
        RegistrationDate registrationDate = new RegistrationDate();
        registrationDate.setDate(date);
        FamilyVoDTO familyDTO1 = new FamilyVoDTO(familyIDTuring, ledgerID, adminEmailTuring, familyNameTuring);
        Family familyTuring = FamilyFactory.create(familyDTO1);
        FamilyVoDTO familyDTO2 = new FamilyVoDTO(familyIDTuring, ledgerID, adminEmailNeumann, familyNameTuring);
        Family familyNeumann = FamilyFactory.create(familyDTO2);
        familyNeumann.setRegistrationDate(registrationDate);
        familyTuring.setRegistrationDate(registrationDate);
        //act
        result = familyNeumann.sameValueAs(familyTuring);
        //assert
        assertFalse(result);
    }


}
